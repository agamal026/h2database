# H2 #4456 — reviewed patch and verification evidence

## Status and exact source

The final four-file fix is published to the user's fork for review. It has **not been submitted to upstream in this handoff**. This packet contains the final patch, executable Java reproducers, actual logs, command receipts, coverage data, and licensing provenance. It includes no complete checkout, dependency jars, credentials, or personal authorship declaration.

- Issue: [h2database/h2database#4456](https://github.com/h2database/h2database/issues/4456).
- Pinned upstream base: [`f2922a7766ec2472bc65d5e5ab4086390a76a42c`](https://github.com/h2database/h2database/commit/f2922a7766ec2472bc65d5e5ab4086390a76a42c).
- Published source: [`agamal026/h2database@9d7a0ad26f8a53654e0ce106a80f702ae5840a28`](https://github.com/agamal026/h2database/commit/9d7a0ad26f8a53654e0ce106a80f702ae5840a28).
- Branch: `fix/4456-any-parameter-types`.
- Published source tree: `07172cddd8b11fa8a5325ccb40072de775f3eaa7`.
- Final patch SHA-256: `12214cbedb78209380b7b4d6c7402752de14b96bb464abe9a10ab224abcf3e98`.
- Diff: four files, 73 insertions and 3 deletions. `fix-4456.patch` is the only patch intended for submission.

Root reviewed the exact final diff with automated code analysis and found no additional correctness issue within the scoped change. **This is automated review, not independent human approval.** The pending contributor declaration must be resolved truthfully before submission; no declaration has been signed or asserted by this packet.

## Problem and correction

On the pinned source, `SELECT COUNT(*) FROM T WHERE V = ANY(?)`, with `V VARCHAR` containing `'01'` and an integer parameter `1`, returns one before creating a `V` index and zero afterward. The original standalone JDBC reproducer was executed against the unmodified source and failed its assertion. The final build returns one in both cases.

The parameter-backed IN-list is evaluated at execution time. `IndexCondition.getCurrentValueList` now checks the actual comparison type with the existing `Comparison.isSafeIndexCondition` helper before converting a value to the index column's type. An unsafe conversion returns `null`, which tells `IndexCursor` to use the remaining usable bounds or scan; the residual SQL predicate still determines matches. An empty array remains an empty list and still means no matches. Safe string scalar/array lookups remain selective.

`IndexCursor.prepare` also clears the underlying cursor together with its other per-execution state. During review, the first draft exposed a real follow-on defect: an unsafe full-index scan stopped early by LIMIT could leave a live cursor; the next safe IN-list lookup read that cursor's remaining rows before its new list. The explicit cursor reset fixes this ordering/duplicate-row regression. The final integrated test fails both the original missing-row implementation and that first draft at distinct assertions.

Changed upstream files are `IndexCondition.java`, `IndexCursor.java`, `TestPreparedStatement.java`, and the next-version changelog. Compound IN-list conversion, ordinary comparison start/end conversion, and unrelated optimizer paths were not changed.

## What actually passed

| Check | Observed result | Evidence |
| --- | --- | --- |
| Final upstream `./build.sh jar` | Exit 0; compilation and Javadoc completed | `logs/final-build.log` |
| Original JDBC reproduction | Unmodified: 1 then 0, assertion fails. Final: 1 then 1, exit 0 | original-reproducer logs |
| Exact integrated regression with original two production classes | Exit 1, expected ID 1 but received ID 2 | `logs/baseline-final-regression.log` |
| Exact integrated regression with first-draft production classes | Exit 1, ordered/LIMIT case expected ID 4 but received ID 2 | `logs/initial-patch-final-regression.log` |
| Exact integrated regression with final production | Exit 0 | `logs/final-final-regression.log` |
| Final complete selected upstream test classes | All five pass: TestPreparedStatement, TestIndex, TestCompoundIndexSearch, TestCompoundIndexParamSearch, TestOptimizations | `logs/final-upstream-selected.log` |
| Differential ordering/rebinding probe | 432 cases per version; original has 160 indexed/scan mismatches; final has zero | ordering logs and comparison receipt |
| JaCoCo changed production lines | 8 of 8 executable lines covered, 4 of 4 new branch outcomes covered | coverage XML and changed-line receipt |
| `git diff --check` | Exit 0 | recorded during final verification |

The integrated method covers 96 parameter-binding/configuration cases plus four safe lookup scan-count controls. These counts describe cases inside one test method, not 96 independent test classes. The selected upstream classes ran with assertions enabled, 128 MiB maximum heap, and `memory=true`. They are a useful focused subset, not a full-suite result.

The 432-case diagnostic crosses four composite-index directions, four deterministic ORDER BY choices, three limits, and nine successive scalar/array/null bindings. The initial draft introduced 10 ordering mismatches. The final version fixes all 160 original mismatches without changing unindexed results and has no mismatches in this matrix. This is measured coverage of these cases, not a proof for every SQL expression.

## Failed, interrupted, or uncompleted gates

Two broad `./build.sh test` attempts ran **before the final cursor-reset correction**. The sandbox-restricted run hit socket-permission failures. The unrestricted run proceeded further but reported failures. Both were deliberately stopped, exit 130; neither is a pass. The final four-file patch has not had a complete broad-suite run.

- `TestOutOfMemory` failed at line 133 after `Connection.close()` did not throw the expected exception. The same assertion was reproduced using unmodified production classes at the pinned base.
- `TestSubqueryPerformanceOnLazyExecutionMode` failed its timing assertion at line 143. Both unmodified production and the first draft reproduce this assertion in isolated targeted runs. Timing results are environment-specific.
- The unrestricted broad run also reported `TestFileLock` lock conflicts, `TestTools` SSL trust-handshake failure, `TestMVStore` write-format failure, and `TestMVStoreBenchmark` timing failure. These were **not baseline-classified**; no claim is made that the final patch passes or causes them.
- Upstream text-format and spell checks each exit 1 on the pristine baseline and final source. After normalizing only the copied working-directory path, their output is identical. Remaining format findings include upstream `TestRecovery.java` formatting and `RandomAccessStore.java` line length; spelling reports the same 17 unknown words. Checks ran on source copies because the format checker can rewrite files.
- The official complete CI suite, full documentation/release checks, and any required maintainer gates remain open. The release script was not executed because it includes a Maven publication target.

No failure or skipped gate is included in the pass counts above. See `receipts/summary.json` for machine-readable status.

## Reproduction and provenance

See `COMMANDS.md` for portable commands. The command JSON files retain actual absolute paths and exit codes from this run. Baseline negative controls use only two exact production classes compiled from the pinned upstream source, placed before the final classpath; all other production code is unchanged by this patch. Source copies for those two classes are included under `baseline-source/`.

The discarded first draft is preserved under `history/initial-patch-not-for-submission.diff` solely to reproduce the second negative control. It is not an alternative release patch. Its SHA-256 is `a58165deed9f6ebb3029dfd084c453fddab290d9f0197d220fd8523adec7d561`.

Toolchain: Eclipse Temurin JDK 17.0.20.1+1 on Linux amd64; Java source compilation used `--release 11`. The official JDK archive SHA-256 is `3808d1d15e3ec6bd5b84057fb5d84c33d8a1536a258146bcea2e603fc726e08e`. Official metadata and download URLs are recorded under `provenance/`. Nineteen build/test jars and five JaCoCo jars were downloaded from Maven Central and verified against checksums pinned in the unchanged upstream build source. Jar binaries and the JDK are excluded from this packet.

The code was prepared with AI assistance. It retains upstream H2 license headers. The pinned repository's `LICENSE.txt` is copied as `provenance/UPSTREAM-LICENSE.txt` and states MPL 2.0 or EPL 1.0 licensing. H2's [official contribution guide](https://h2database.com/html/build.html#submitting_source_code_changes), also available at the [pinned source](https://github.com/h2database/h2database/blob/f2922a7766ec2472bc65d5e5ab4086390a76a42c/h2/src/docsrc/html/build.html), requires suitable tests, style, changed-code coverage, build/documentation checks, and a contributor statement for significant work. This packet neither makes a legal statement on the user's behalf nor claims that AI involvement is automatically accepted.

No bounty, reward qualification, maintainer acceptance, or payment is claimed. Before an upstream PR, recover this exact source and evidence, resolve the truthful contributor-statement requirement, and make the failed/incomplete gates visible to reviewers.

Every text artifact is listed with its SHA-256 in `SHA256SUMS` and `manifest.json`.
