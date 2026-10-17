# H2 #4456 hosted CI result

The existing workflow run [39073428774](https://github.com/agamal026/h2database/actions/runs/39073428774) completed successfully on exact commit `9d7a0ad26f8a53654e0ce106a80f702ae5840a28`, tree `07172cddd8b11fa8a5325ccb40072de775f3eaa7`, attempt 1. The completed result was observed at 2026-10-17T22:25:01Z.

Both Java 11 and Java 17 jobs ran the unchanged upstream `./build.sh jar testCI` command successfully. This compiles/generates Javadoc and executes H2's complete CI-selected suite through six database configurations plus additional/utility tests. The changed TestPreparedStatement class completed in all six configurations on each JDK. This is substantially broader than the earlier five-class local subset.

Complete decoded job logs are retained under `logs/`; each begins at runner setup and reaches terminal cleanup. They are not truncated. Byte sizes and SHA-256 values in `receipt.json` identify the UTF-8 encoded decoded content as received, not an Actions ZIP archive. `terminal-evidence.md` is an explicitly selected excerpt and includes the retained Java 11 background-memory diagnostic context.

## Results and limitations

- Java 11 job 117515577514: success; Test finished 22:23:10Z, job 22:23:12Z. Runtime 11.0.32.1+1; TestAll total 20:11.648.
- Java 17 job 117515577740: success; Test finished 22:23:58Z, job 22:24:00Z. Runtime 17.0.20.1+1; TestAll total 21:00.460.
- Both logs have zero `ERROR: FAIL` and zero durability-failure lines. Both report five classes not executed: TestDefrag, TestMVStoreBenchmark, TestSubqueryPerformanceOnLazyExecutionMode, TestSpatial, TestLargeBlob.
- Java 11 additionally contains one GitHub error annotation for an OutOfMemoryError in a background MVStore writer during the intentional memory-pressure test. That class subsequently completed normally and the job passed. Its source deliberately starves a child process of memory, but whether this exact uncaught background diagnostic is expected is not established. One read of the already completed exact-upstream-base Java 11 log found no equivalent diagnostic. No rerun was performed, and the packet does not assert causation or baseline reproduction.
- The source runs the complete CI selection, not every non-CI slow/big/SSL mode. Existing non-CI failures remain historical failures. Root's separate docs gate still fails on the same baseline format findings; hosted testCI does not validate the complete docs gate.
- The truthful contributor licensing/authorship statement remains pending. Automated review and hosted CI do not establish independent human approval or upstream acceptance.

The receipt includes only allowlisted run/job fields, exact source URLs, measured outcomes and limitations. The complete logs are public Actions output preserved as received; no credentials were added. No source changes, workflow dispatches, reruns or publishing were performed by this verification task.
