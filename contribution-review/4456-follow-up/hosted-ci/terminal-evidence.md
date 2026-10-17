# Hosted CI terminal evidence — run 39073428774

This is a selected excerpt from the complete decoded logs, not a complete log. Complete logs and hashes are retained separately. The full logs were retrieved only after both jobs completed.

## java11

```text
﻿2026-10-17T22:02:34.6946455Z Current runner version: '2.337.0'
2026-10-17T22:02:42.0864105Z Target: jar
2026-10-17T22:02:58.0296839Z Target: testCI
2026-10-17T22:02:58.4464859Z /usr/lib/jvm/temurin-11-jdk-amd64/bin/java -ea -Xmx128m -XX:MaxDirectMemorySize=2g -Duser.timezone=Etc/UTC -cp src/java10/precompiled:src/java9/precompiled:temp:bin:ext/postgresql-42.7.2.jar:ext/javax.servlet-api-4.0.1.jar:ext/jakarta.servlet-api-5.0.0.jar:ext/lucene-core-9.7.0.jar:ext/lucene-analysis-common-9.7.0.jar:ext/lucene-queryparser-9.7.0.jar:ext/org.osgi.core-5.0.0.jar:ext/org.osgi.service.jdbc-1.1.0.jar:ext/jts-core-1.19.0.jar:ext/slf4j-api-2.0.7.jar:ext/slf4j-nop-2.0.7.jar:ext/asm-9.5.jar:/usr/lib/jvm/temurin-11-jdk-amd64/../lib/tools.jar org.h2.test.TestAll ci
2026-10-17T22:03:55.3532454Z 22:03:55 00:00.312 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:09:27.0408049Z 22:09:27 00:00.229 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:12:05.6194113Z 22:12:05 00:00.240 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:15:42.8071707Z 22:15:42 00:00.965 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:18:07.5192676Z 22:18:07 00:00.892 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:20:29.9582390Z 22:20:29 00:00.457 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:23:10.2861448Z Warning: test org.h2.test.store.TestDefrag was not executed.
2026-10-17T22:23:10.2862334Z Warning: test org.h2.test.store.TestMVStoreBenchmark was not executed.
2026-10-17T22:23:10.2863067Z Warning: test org.h2.test.db.TestSubqueryPerformanceOnLazyExecutionMode was not executed.
2026-10-17T22:23:10.2863798Z Warning: test org.h2.test.db.TestSpatial was not executed.
2026-10-17T22:23:10.2864386Z Warning: test org.h2.test.db.TestLargeBlob was not executed.
2026-10-17T22:23:10.2864824Z 20:11.648 total
2026-10-17T22:23:10.3309793Z Done in 1228247 ms
2026-10-17T22:23:10.7333352Z Cleaning up orphan processes
```

## java17

```text
﻿2026-10-17T22:02:34.7945127Z Current runner version: '2.337.0'
2026-10-17T22:02:38.8249631Z Target: jar
2026-10-17T22:02:57.4893974Z Target: testCI
2026-10-17T22:02:58.0104698Z /usr/lib/jvm/temurin-17-jdk-amd64/bin/java -ea -Xmx128m -XX:MaxDirectMemorySize=2g -Duser.timezone=Etc/UTC -cp src/java10/precompiled:src/java9/precompiled:temp:bin:ext/postgresql-42.7.2.jar:ext/javax.servlet-api-4.0.1.jar:ext/jakarta.servlet-api-5.0.0.jar:ext/lucene-core-9.7.0.jar:ext/lucene-analysis-common-9.7.0.jar:ext/lucene-queryparser-9.7.0.jar:ext/org.osgi.core-5.0.0.jar:ext/org.osgi.service.jdbc-1.1.0.jar:ext/jts-core-1.19.0.jar:ext/slf4j-api-2.0.7.jar:ext/slf4j-nop-2.0.7.jar:ext/asm-9.5.jar:/usr/lib/jvm/temurin-17-jdk-amd64/../lib/tools.jar:ext/nashorn-core-15.4.jar:ext/asm-commons-9.5.jar:ext/asm-tree-9.5.jar:ext/asm-util-9.5.jar org.h2.test.TestAll ci
2026-10-17T22:03:52.7552357Z 22:03:52 00:00.311 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:09:25.9912947Z 22:09:25 00:00.204 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:11:57.9297341Z 22:11:57 00:00.285 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:15:31.3871593Z 22:15:31 00:01.056 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:18:21.2863882Z 22:18:21 00:01.156 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:21:03.9957661Z 22:21:03 00:00.584 org.h2.test.jdbc.TestPreparedStatement 
2026-10-17T22:23:58.6302557Z Warning: test org.h2.test.store.TestMVStoreBenchmark was not executed.
2026-10-17T22:23:58.6306638Z Warning: test org.h2.test.store.TestDefrag was not executed.
2026-10-17T22:23:58.6307500Z Warning: test org.h2.test.db.TestSubqueryPerformanceOnLazyExecutionMode was not executed.
2026-10-17T22:23:58.6308627Z Warning: test org.h2.test.db.TestSpatial was not executed.
2026-10-17T22:23:58.6309294Z Warning: test org.h2.test.db.TestLargeBlob was not executed.
2026-10-17T22:23:58.6311503Z 21:00.460 total
2026-10-17T22:23:58.6920500Z Done in 1279868 ms
2026-10-17T22:23:59.1318405Z Cleaning up orphan processes
```

## Java 11 background OOM diagnostic — exact surrounding context

The test class subsequently completed normally; the exact-base Java11 log has no equivalent error marker. This packet does not classify the cause as resolved.

```text
2026-10-17T22:13:51.3899203Z 22:13:51 00:05.996 org.h2.test.server.TestWeb 
2026-10-17T22:13:51.3908181Z 22:13:51 00:00.000 org.h2.test.server.TestJakartaWeb 
2026-10-17T22:13:53.6967922Z 22:13:53 00:02.305 org.h2.test.db.TestOptimizations 
2026-10-17T22:13:55.5471461Z ##[error]Exception in thread "MVStore background writer /home/runner/work/h2database/h2database/h2/data/test/outOfMemory.mv.db" org.h2.mvstore.MVStoreException: java.lang.OutOfMemoryError: GC overhead limit exceeded [2.5.259/3]
2026-10-17T22:13:55.5480619Z 	at org.h2.mvstore.DataUtils.newMVStoreException(DataUtils.java:998)
2026-10-17T22:13:55.5481170Z 	at org.h2.mvstore.MVStore.panic(MVStore.java:515)
2026-10-17T22:13:55.5481617Z 	at org.h2.mvstore.MVStore.storeNow(MVStore.java:992)
2026-10-17T22:13:55.5482207Z 	at org.h2.mvstore.MVStore.store(MVStore.java:950)
2026-10-17T22:13:55.5482686Z 	at org.h2.mvstore.MVStore.tryCommit(MVStore.java:885)
2026-10-17T22:13:55.5483116Z 	at org.h2.mvstore.MVStore.tryCommit(MVStore.java:878)
2026-10-17T22:13:55.5483823Z 	at org.h2.mvstore.FileStore.writeInBackground(FileStore.java:1905)
2026-10-17T22:13:55.5484683Z 	at org.h2.mvstore.FileStore$BackgroundWriterThread.run(FileStore.java:2319)
2026-10-17T22:13:55.5485922Z 	Suppressed: org.h2.message.DbException: General error: "org.h2.mvstore.MVStoreException: java.lang.OutOfMemoryError: GC overhead limit exceeded [2.5.259/3]" [50000-259]
2026-10-17T22:13:55.5487038Z 		at org.h2.message.DbException.get(DbException.java:212)
2026-10-17T22:13:55.5487791Z 		at org.h2.message.DbException.convert(DbException.java:407)
2026-10-17T22:13:55.5488608Z 		at org.h2.mvstore.db.Store.lambda$new$0(Store.java:123)
2026-10-17T22:13:55.5489293Z 		at org.h2.mvstore.MVStore.handleException(MVStore.java:1693)
2026-10-17T22:13:55.5490035Z 		at org.h2.mvstore.MVStore.panic(MVStore.java:521)
2026-10-17T22:13:55.5490666Z 		... 7 more
2026-10-17T22:13:55.5491709Z 	Caused by: org.h2.jdbc.JdbcSQLNonTransientException: General error: "org.h2.mvstore.MVStoreException: java.lang.OutOfMemoryError: GC overhead limit exceeded [2.5.259/3]" [50000-259]
2026-10-17T22:13:55.5493375Z 		at org.h2.message.DbException.getJdbcSQLException(DbException.java:566)
2026-10-17T22:13:55.5494231Z 		at org.h2.message.DbException.getJdbcSQLException(DbException.java:489)
2026-10-17T22:13:55.5494871Z 		... 12 more
2026-10-17T22:13:55.5496135Z 	Caused by: [CIRCULAR REFERENCE: org.h2.mvstore.MVStoreException: java.lang.OutOfMemoryError: GC overhead limit exceeded [2.5.259/3]]
2026-10-17T22:13:55.5497286Z Caused by: java.lang.OutOfMemoryError: GC overhead limit exceeded
2026-10-17T22:13:55.5498248Z 	at java.base/java.lang.invoke.MethodType$ConcurrentWeakInternSet.get(MethodType.java:1279)
2026-10-17T22:13:55.5499159Z 	at java.base/java.lang.invoke.MethodType.makeImpl(MethodType.java:300)
2026-10-17T22:13:55.5500139Z 	at java.base/java.lang.invoke.MethodTypeForm.canonicalize(MethodTypeForm.java:355)
2026-10-17T22:13:55.5501064Z 	at java.base/java.lang.invoke.MethodTypeForm.findForm(MethodTypeForm.java:317)
2026-10-17T22:13:55.5501903Z 	at java.base/java.lang.invoke.MethodType.makeImpl(MethodType.java:315)
2026-10-17T22:13:55.5503149Z 	at java.base/java.lang.invoke.MethodHandleNatives.findMethodHandleType(MethodHandleNatives.java:376)
2026-10-17T22:13:55.5504595Z 	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer$Node.compareAndSetWaitStatus(AbstractQueuedSynchronizer.java:533)
2026-10-17T22:13:55.5506359Z 	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer.transferForSignal(AbstractQueuedSynchronizer.java:1717)
2026-10-17T22:13:55.5507918Z 	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.doSignal(AbstractQueuedSynchronizer.java:1917)
2026-10-17T22:13:55.5509408Z 	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.signal(AbstractQueuedSynchronizer.java:1984)
2026-10-17T22:13:55.5510800Z 	at java.base/java.util.concurrent.LinkedBlockingQueue.signalNotEmpty(LinkedBlockingQueue.java:175)
2026-10-17T22:13:55.5511929Z 	at java.base/java.util.concurrent.LinkedBlockingQueue.offer(LinkedBlockingQueue.java:421)
2026-10-17T22:13:55.5513183Z 	at java.base/java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1347)
2026-10-17T22:13:55.5514361Z 	at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:118)
2026-10-17T22:13:55.5515327Z 	at org.h2.mvstore.FileStore.submitOrRun(FileStore.java:1446)
2026-10-17T22:13:55.5516126Z 	at org.h2.mvstore.FileStore.storeIt(FileStore.java:1437)
2026-10-17T22:13:55.5516813Z 	at org.h2.mvstore.MVStore.storeNow(MVStore.java:983)
2026-10-17T22:13:55.5517401Z 	... 5 more
2026-10-17T22:13:55.8754347Z 22:13:55 00:02.178 org.h2.test.db.TestOutOfMemory 
2026-10-17T22:13:55.9080920Z 22:13:55 00:00.032 org.h2.test.db.TestIgnoreCatalogs 
2026-10-17T22:13:55.9552907Z 22:13:55 00:00.015 org.h2.test.store.TestMVTableEngine 
```
