# H2 #4456 — completed hosted CI and documentation follow-up

This evidence supplements the [initial immutable review packet](https://github.com/agamal026/h2database/blob/0feb07c57de0641cda13ad69de24ecf88fc4c917/contribution-review/4456/README.md).
The original packet and its checksum inventories remain unchanged. This
follow-up contains no source or workflow changes.

## Exact proposal and review status

- Issue: [h2database/h2database #4456](https://github.com/h2database/h2database/issues/4456).
- Source commit: [`9d7a0ad26f8a53654e0ce106a80f702ae5840a28`](https://github.com/agamal026/h2database/commit/9d7a0ad26f8a53654e0ce106a80f702ae5840a28).
- Source tree: `07172cddd8b11fa8a5325ccb40072de775f3eaa7`.
- Upstream base: `f2922a7766ec2472bc65d5e5ab4086390a76a42c`.
- Four-file patch SHA-256: `12214cbedb78209380b7b4d6c7402752de14b96bb464abe9a10ab224abcf3e98`.
- [Review draft #1](https://github.com/agamal026/h2database/pull/1) is in the contributor's fork. No upstream submission, human approval, reward eligibility or payment is established.

The change preserves results when a VARCHAR index is compared with a differently
typed `ANY(?)` binding. It checks comparison safety before converting index keys
and clears reused cursor state before each execution. The initial packet
retains the reproduction, discarded-draft negative control, final regressions,
safe index-use controls, selected complete test classes and coverage evidence.

## Hosted Java 11 and Java 17 CI succeeded

[Run 39073428774](https://github.com/agamal026/h2database/actions/runs/39073428774)
ran the unchanged upstream workflow on the exact source commit above. Both jobs
and their Test steps concluded successfully on attempt 1. The command was
`./build.sh jar testCI` from `h2`, with `JAVA_OPTS=-Xmx512m` set by the workflow.

| Job | Runtime | Test completed, UTC | Result |
| --- | --- | --- | --- |
| [Java 11](https://github.com/agamal026/h2database/actions/runs/39073428774/job/117515577514) | Temurin 11.0.32.1+1 | 2026-10-17 22:23:10 | Success |
| [Java 17](https://github.com/agamal026/h2database/actions/runs/39073428774/job/117515577740) | Temurin 17.0.20.1+1 | 2026-10-17 22:23:58 | Success |

This is the complete upstream **CI-selected suite**, including six database
configurations and additional/utility tests. `TestPreparedStatement` completed
in all six configurations on each JDK. Both logs report five unexecuted classes:
`TestDefrag`, `TestMVStoreBenchmark`, `TestSubqueryPerformanceOnLazyExecutionMode`,
`TestSpatial`, and `TestLargeBlob`. It does not cover every slower non-CI mode.

The passing Java 11 job includes one error annotation for a background MVStore
`OutOfMemoryError` during `TestOutOfMemory`. That test deliberately puts a child
process under memory pressure, then records normal completion. Neither final
log contains a framework `ERROR: FAIL` or a durability-failure line. The existing
successful exact-base Java 11 log lacks this diagnostic. **The cause of the exact
background diagnostic remains unresolved; baseline reproduction is not claimed.**

[The hosted-CI receipt](hosted-ci/receipt.json), [selected terminal excerpts](hosted-ci/terminal-evidence.md)
and complete decoded logs retain those details, skipped-class warnings and
source references. [A second canonical read-back](hosted-ci-canonical.json)
records the actual run, jobs and step conclusions. No rerun was needed for this
evidence collection.

## Documentation gate remains failed

The [command receipts](docs-command-receipts.json) and complete logs preserve
both attempts in an isolated copy of the exact final source on Temurin 17:

1. `./build.sh docs` exited 1 because the clean copy lacked prerequisite compiled
   H2 classes. This was a preparation failure.
2. `./build.sh jar docs` first completed the JAR build and Javadoc, then exited 1
   during `CheckTextFiles`. It reported an automatic `TestRecovery.java` rewrite
   and `RandomAccessStore.java:271`, line too long: 131. Later documentation
   generation/link checks were not reached.

The blocking `RandomAccessStore.java` is byte-identical to the pinned upstream
base, SHA-256 `f99cf0790791a587b00c6bdbc8c5c4b3527db0b3735ec3df68649a4209e7b394`.
The same formatting finding appears in the initial baseline evidence. This
comparison identifies the unchanged blocking file; it does not convert the
failed documentation command into a pass. Formatter changes were confined to
the isolated copy. The four original source files still match the published
proposal.

The earlier failed/interrupted non-CI broad runs remain historical failures.
There is no completed final-patch pass of every non-CI mode or the full
documentation/release process. The truthful contributor authorship/licensing
statement required by [H2's contribution guide](https://h2database.com/html/build.html#providing_patches)
also remains pending. Automated review and successful CI do not supply that
personal statement.

## Inventory

The top-level `manifest.json` and `SHA256SUMS` cover all payload files, excluding
those two inventory files themselves. The independent `hosted-ci/` packet also
retains its original internal inventories unchanged. Hashes describe the UTF-8
files as saved; CI logs are complete decoded bodies, not an Actions ZIP archive.
