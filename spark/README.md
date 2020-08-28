## Configuration
We used PureConfig to read config from an application.conf resources, a URL and literal config.
__Literal config overwrite the config from URL which in turn overwrites config from an application.conf__

Usage: 
```
$ spark-submit ... <application-jar>  [options]
```

Available options:
*  -j, --job <value>        job is a required application name property
*  -n, --namespace <value>  optional configuration namespace property
*  -u, --url <value>        optional config url property
*  -l, --literal <value>    optional literal config property
  
#### Example:
submit:
```
./bin/spark-submit \
  --class <main-class> \
  --master <master-url> \
  --deploy-mode <deploy-mode> \
  --conf <key>=<value> \
  ... # other options
  <application-jar> \
  -j JobName \
  -n events \
  -u http://localhost:3000/conf \
  -l "{events: {input = gcs://bucket/input/file.avro}"
```
application.conf
```
events {
    input = "file://tmp/dev.avro"
    output = "file://tmp/output/"
    mapping = {
        "name": "fullName",
        "id": "PK"
    }
}
```
http://localhost:3000/conf
```
{
  "events": {
    "input" : http://localhost
    "output": "gcs://bucket/output/"
  }
}
```
__Final Config__
```
input = gcs://bucket/input/file.avro
output = gcs://bucket/output/
mapping = {
    "name": "fullName",
    "id": "PK"
}
``` 


