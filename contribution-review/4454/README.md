# H2 #4454: actual-source checkpoint and engine validation

Source candidate: [40b3bda1bac76ab59e9b0e4d54ac1935a398685b](https://github.com/agamal026/h2database/commit/40b3bda1bac76ab59e9b0e4d54ac1935a398685b).
Base: [f2922a7766ec2472bc65d5e5ab4086390a76a42c](https://github.com/h2database/h2database/commit/f2922a7766ec2472bc65d5e5ab4086390a76a42c).
Issue: [h2database/h2database#4454](https://github.com/h2database/h2database/issues/4454).

The candidate contains the actual production file and integrated test change, not only a patch attachment. It is a contributor-fork checkpoint, not an upstream PR. Its publication resumes the prepared work after the owner explicitly repaired ChatGPT's GitHub permissions.

## Recovery correction

The old proposal was generated from source snippets. Its recorded Git blob identities match the recovered complete baseline files, but its line offsets and test-hunk context do not apply directly to those complete files. The reviewed mapping change and two regression methods were integrated at unique verified anchors without dropping any pre-existing test calls. The original archive and patch remain unchanged in private storage. The two changed baseline Git blobs were verified locally before editing.

| File | Baseline Git blob | Candidate Git blob |
| --- | --- | --- |
| IndexCondition.java | a3bb793b9e35097bf5bc87499e59b1a98561c608 | 6f0e489f73d1c5f3c4601a88c5e580b550c2ad76 |
| TestCompoundIndexSearch.java | 2a0956a75465660b713f0d64d53207894dc0cf13 | 38c33ff5d9df5e29bb8f0f8df1036d73ac9d163a |

## Completed and pending gates at publication

Completed: exact-file recovery, original-packet integrity verification, focused diff inspection, whitespace check, source commit read-back, non-forced branch publication and branch read-back. The earlier exhaustive Java model checks are reused as model evidence only; they are not H2 integration tests. This session's inspection is self-review, not an independent agent or personal contributor review.

Pending: real H2 baseline/final JDBC results and selective index-plan control; complete upstream CI at the source candidate; changed-code coverage; changelog and all remaining upstream documentation/style/non-CI requirements; truthful contributor statement and authorized upstream publication. A workflow launch is not a passing result.

The existing upstream Java 11/17 CI has started at [run 39116241496](https://github.com/agamal026/h2database/actions/runs/39116241496). Preserve its actual outcome and logs; do not confuse it with the older #4456 run.

This branch's scoped workflow builds both exact source revisions and runs `Run4454.java` against the real H2 JAR. Baseline jobs must confirm the specific duplicate-column internal error and the independent permutation wrong-result; candidate jobs must return the correct rows and show selective, correctly ordered index bounds. Any different failure remains a failure to investigate, not a reproduced defect.

The evidence branch is not the proposed source branch. Its workflow has read-only repository permission, no timer, no publishing step and no external credentials. H2's contribution policy was reread at https://h2database.com/html/build.html#providing_patches. No human authorship statement, acceptance, payment, or campaign submission is claimed.
