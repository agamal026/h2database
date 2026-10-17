# Source and toolchain provenance

- Upstream issue: https://github.com/h2database/h2database/issues/4456
- Exact upstream code: https://github.com/h2database/h2database/tree/f2922a7766ec2472bc65d5e5ab4086390a76a42c
- Exact published fix: https://github.com/agamal026/h2database/commit/9d7a0ad26f8a53654e0ce106a80f702ae5840a28
- Upstream license: https://github.com/h2database/h2database/blob/f2922a7766ec2472bc65d5e5ab4086390a76a42c/LICENSE.txt
- Contribution/build guide: https://github.com/h2database/h2database/blob/f2922a7766ec2472bc65d5e5ab4086390a76a42c/h2/src/docsrc/html/build.html
- Official hosted guide: https://h2database.com/html/build.html#submitting_source_code_changes
- Dependency versions/checksums: https://github.com/h2database/h2database/blob/f2922a7766ec2472bc65d5e5ab4086390a76a42c/h2/src/tools/org/h2/build/Build.java
- Temurin metadata API: https://api.adoptium.net/v3/assets/latest/17/hotspot?architecture=x64&image_type=jdk&os=linux&vendor=eclipse
- Exact JDK archive: https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.20.1%2B1/OpenJDK17U-jdk_x64_linux_hotspot_17.0.20.1_1.tar.gz
- JDK archive SHA-256: `3808d1d15e3ec6bd5b84057fb5d84c33d8a1536a258146bcea2e603fc726e08e`.

The dynamic metadata API response used for this run is preserved in `temurin-official-metadata.json`. The exact release URL and checksum identify the actual downloaded archive. Build/test and coverage dependency JSON files record the public Maven Central URL, expected upstream-pinned SHA-1, byte size, and verification result. Those upstream hashes are provenance checks used by the H2 build, not a newly asserted security guarantee for SHA-1.

The patch retains the H2 source headers. `UPSTREAM-LICENSE.txt` is the exact base repository license. The baseline source copies are the corresponding original upstream files. No contributor signature or human-only authorship declaration is provided.
