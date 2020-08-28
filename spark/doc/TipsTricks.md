# Apache Spark Tips and Tricks
## Configuration
### Data Serialization
```
# Kryo Serializer is much faster than the default Java Serializer
spark.serializer="org.apache.spark.serializer.KryoSerializer"
```
### Shuffle
```
# default is 200 which can be too high for some jobs
spark.conf.set("spark.sql.shuffle.partitions", 10)   
```
### Logn runig Spark Streaming
SPARK STREAMING CONFIGURATION
```
# [Optional] Tweak to balance data processing parallelism vs. task scheduling overhead (Default: 200ms)
spark.streaming.blockInterval=200 

# Prevent data loss on driver recovery
spark.streaming.receiver.writeAheadLog.enable=true 

spark.streaming.backpressure.enabled=true

# [Optional] Reduce min rate of PID-based backpressure implementation (Default: 100)
spark.streaming.backpressure.pid.minRate=10 

# [Spark 1.x]: Workaround for missing initial rate (Default: not set)
spark.streaming.receiver.maxRate=100 

# [Spark 1.x]: Corresponding max rate setting for Direct Kafka Streaming (Default: not set)
spark.streaming.kafka.maxRatePerPartition=100

# [Spark 2.x]: Initial rate before backpressure kicks in (Default: not set) 
spark.streaming.backpressure.initialRate=30 
```
YARN CONFIGURATION
```
# [Optional] Set if --driver-memory < 5GB
spark.yarn.driver.memoryOverhead=1024 

# [Optional] Set if --executor-memory < 10GB
spark.yarn.executor.memoryOverhead=1024

# Increase max application master attempts (needs to be <= yarn.resourcemanager.am.max-attempts in YARN, which defaults to 2) (Default: yarn.resourcemanager.am.max-attempts)
spark.yarn.maxAppAttempts=4 

# Attempt counter considers only the last hour (Default: (none))
spark.yarn.am.attemptFailuresValidityInterval=1h 

# Increase max executor failures (Default: max(numExecutors * 2, 3))
spark.yarn.max.executor.failures=$((8 * ${num_executors}))

# Executor failure counter considers only the last hour
spark.yarn.executor.failuresValidityInterval=1h 
```
### Scaling for large workloads
Dynamic resources allocation
```
spark.shuffle.service.enabled=true 
spark.dynamicAllocation.enabled=true // default false

# executors to start with
spark.dynamicAllocation.minExecutors=2

# if task queue backlog increases, new executors will be requested each time the backlog timeout
spark.dynamicAllocation.schedulerBacklogTimeout=1m 

# maximum executors 
spark.dynamicAllocation.maxExecutors=20

# terminate the executor which finishes a task and is idle for this period of time  
spark.dynamicAllocation.executorIdleTimeout=2min
```
Memory and Shuffle
```
# Only change this if you expect the driver to receive large amounts of data
back from operations like collect(), or if you run out of driver memory
spark.driver.memory=1024 // default 1GB

# Allows Spark to do more buffering before writing final map results to disk
spark.shuffle.file.buffer=1MB // default 32KB

# Force Spark to use the file buffer to transfer files before finally writing to disk; this will decrease the I/O activity
spark.file.transferTo=false  // default true

# Controls the amount of buffering possible when merging files during shuffle operations.
spark.shuffle.unsafe.file.output.buffer=1MB // default 32 KB 

# Decrease the size of the shuffle file by increasing the compressed size of the block.
spark.io.compression.lz4.blockSize=512KB

# Cache entries are limited to the specified memory footprint in byte
spark.shuffle.service.index.cache.size=100m

spark.shuffle.registration.timeout=120000ms // default 5000 ms
spark.shuffle.registration.maxAttempts=5 // default 3
```
## Programming
### Joins
Put the Largest Dataset on the Left
```
val joinedDF = largerDF.leftJoin(smallerDF, largerDF("id") === smallerDF("some_id"))
```
Broadcast Joining for joining Smaller Datasets to Larger Ones
```
val joinedDF = largeDF.join(broadcast(smallDF), largeDF("id") === smallDF("some_id"))
```
### Feed as much input as possible
Reading all files at once
```
  val dates = Seq("2020-07-07", "2020-07-09")

  val filesPath = dates.map(date => {
    val Array(year, month, day) = date.split("-")
    "webhdfs://sandbox-hdp.hortonworks.com:50070/data/exchange-rates/y=" + year + "/m=" + month + "/d=" + day
  })

  val df = spark.read.json(filesPath: _*)
```
### Skip schema inference
```
  val conf = ConfigFactory.parseResources("schema.conf")
  val jsonSchema = conf.root().render(ConfigRenderOptions.concise)
  val schema = DataType.fromJson(jsonSchema).asInstanceOf[StructType]
  
  val df = spark.read.schema(schema).json(filesPath: _*)
```
### Optimizing Shuffle
If your query causes a shuffle, try to apply some filters to reduce the amount of data that needs to be transferred
```
```
### Coalesce vs Repartition
_Coalesce_ should be preferred over _repartition_ to reduce the number of partitions because it avoids a _shuffle_, but *the number of tasks depends on the number of partitions of the output of the stage*
```
df.doSth().coalesce(10).write(…) // slower

df.doSth().repartition(10).write(…) // faster
```




