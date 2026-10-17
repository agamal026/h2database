# Portable reproduction commands

The exact commands and exit statuses already executed are in `receipts/*commands.json`. These instructions replace the original cloud paths with explicit local paths. Use isolated directories: H2 tests create `./data`. Java 11 source compatibility was tested with JDK 17. The commands below are provided for continuation; packaging did not rerun tests.

## Obtain the published final source

```sh
git clone --branch fix/4456-any-parameter-types https://github.com/agamal026/h2database.git h2-4456
cd h2-4456
git checkout 9d7a0ad26f8a53654e0ce106a80f702ae5840a28
```

Alternatively check out upstream commit `f2922a7766ec2472bc65d5e5ab4086390a76a42c` in an isolated checkout, then run `git apply --check /absolute/path/to/fix-4456.patch` and `git apply /absolute/path/to/fix-4456.patch`.

## Build and run the reproducible focused checks

Set these to existing absolute paths. The proof directory must be dedicated to this investigation.

```sh
H2_SOURCE=/absolute/path/to/h2-4456
H2_JDK=/absolute/path/to/jdk-17
H2_PACKET=/absolute/path/to/h2-4456-review-text
H2_PROOF=/absolute/path/to/h2-4456-proof
mkdir -p "$H2_PROOF/classes" "$H2_PROOF/work"
cd "$H2_SOURCE/h2"
JAVA_HOME="$H2_JDK" ./build.sh jar
"$H2_JDK/bin/javac" --release 11 -cp "$H2_SOURCE/h2/temp" -d "$H2_PROOF/classes" "$H2_PACKET"/repro/*.java
cd "$H2_PROOF/work"
"$H2_JDK/bin/java" -ea -cp "$H2_PROOF/classes:$H2_SOURCE/h2/temp:$H2_SOURCE/h2/ext/*" Reproduce4456
"$H2_JDK/bin/java" -ea -cp "$H2_PROOF/classes:$H2_SOURCE/h2/temp:$H2_SOURCE/h2/ext/*" RunRegression4456
"$H2_JDK/bin/java" -ea -cp "$H2_PROOF/classes:$H2_SOURCE/h2/temp:$H2_SOURCE/h2/ext/*" OrderProbe4456
"$H2_JDK/bin/java" -ea -Xmx128m -cp "$H2_PROOF/classes:$H2_SOURCE/h2/temp:$H2_SOURCE/h2/ext/*" RunSelectedH2Tests org.h2.test.jdbc.TestPreparedStatement org.h2.test.db.TestIndex org.h2.test.db.TestCompoundIndexSearch org.h2.test.db.TestCompoundIndexParamSearch org.h2.test.db.TestOptimizations
```

Expected final outputs: original reproducer reports `without index: 1; with index: 1`; integrated regression passes; ordering probe reports `cases=432` and `mismatches=0`; the selected-class launcher prints five PASS lines. The diagnostic ordering launcher reports mismatches but does not itself return a failure exit when mismatches are found; inspect its SUMMARY and individual indexed/scan pairs.

H2's normal build downloads its pinned dependencies. In the original cloud environment Java's downloader did not use the available proxy, so curl downloaded the exact URLs listed in the provenance JSON and the upstream-pinned SHA-1 values were verified. Do not replace dependencies with arbitrary versions. The normal build's initial network failure was not a compilation result; the successful offline build log records the actual compilation.

## Original-production negative control

The baseline source directory contains only the two changed production classes, extracted from the exact upstream base. Compile them against the built checkout, then place them before its production classes on the classpath:

```sh
mkdir -p "$H2_PROOF/baseline-classes" "$H2_PROOF/baseline-work"
"$H2_JDK/bin/javac" --release 11 -cp "$H2_SOURCE/h2/temp" -d "$H2_PROOF/baseline-classes" "$H2_PACKET"/baseline-source/org/h2/index/*.java
cd "$H2_PROOF/baseline-work"
"$H2_JDK/bin/java" -ea -cp "$H2_PROOF/baseline-classes:$H2_PROOF/classes:$H2_SOURCE/h2/temp:$H2_SOURCE/h2/ext/*" RunRegression4456
```

Expected exit is 1 with `Expected: 1 actual: 2`. This deliberately executes the final integrated test against original production logic. It is evidence that the test detects the reported bug.

For the first-draft negative control, use a separate checkout at the upstream base, apply `history/initial-patch-not-for-submission.diff`, compile its two production classes into a separate directory, and place that directory before the final checkout on the same regression classpath. Expected exit is 1 with `Expected: 4 actual: 2`. Do not replace the final patch with that historical draft.

## Changed-code coverage

JaCoCo 0.8.10 was used with the five pinned dependencies in `provenance/coverage-dependencies.json` and `jacocoagent.jar` extracted from the pinned agent jar. The exact agent, include filter, report command, working directory, and exit statuses are in `receipts/final-verification-commands.json`. The probe executed the integrated regression, including the final cursor-reset correction. `coverage/final-coverage.xml` plus `receipts/final-changed-line-coverage.json` preserve the result: 8/8 changed executable lines and 4/4 new branch outcomes.

## Existing failed checks and required continuation

Actual failed command lines are retained in the broad-run logs and baseline-failure receipt. Both broad runs were interrupted with exit 130 and were performed before the final cursor-reset correction. No final full-suite pass exists. To satisfy the upstream full-suite gate, use the official guide and a suitable environment; the corresponding upstream CI command is `./build.sh jar testCI`. This packet does not claim that command was run successfully.

The source format checker can auto-rewrite files, so run it on copied source trees. Baseline and final spell/text results in this packet are identical after only working-directory normalization and both remain failed. Do not execute the release installer script as a routine check; it contains external Maven publication.
