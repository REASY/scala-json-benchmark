# A Scala JSON serialization/deserialization benchmark

### Introduction
When decision about JSON library should be made because of performance, this benchmark can be helpful. Benchmarked libraries:
- [Argonaut](https://github.com/argonaut-io/argonaut)
- [circe](https://github.com/circe/circe)
- [JSON4S](https://github.com/json4s/json4s)
- [Lift](https://github.com/lift/framework/tree/master/core/json)
- [spray-json](https://github.com/spray/spray-json)
- [play-json](https://github.com/playframework/play-json)
- [jsoniter-scala](https://github.com/plokhotnyuk/jsoniter-scala)

This project also demonstrates how to use these different JSON libraries to serialize/deserialize a Scala `case class`.

### Benchmark
Dataset and types ([Bird](src/main/scala/models/Bird.scala) and [Place](src/main/scala/models/Place.scala)) are from this repo: [scala-json-benchmark](https://github.com/nlw0/scala-json-benchmark). For benchmark used [jmh](https://openjdk.java.net/projects/code-tools/jmh/) via [sbt-jmh plugin](https://github.com/ktoso/sbt-jmh/). File [birds.data](src/main/resources/birds.data) contains 25000 lines. Each of line is serialized json of `Bird`.
- Serialization benchmark is in [SerializationBenchmark.scala](src/main/scala/benchmarks/SerializationBenchmark.scala). To run it use `jmh:run -i 5 -wi 5 -f1 -t1 .*SerializationBenchmark*` in `SBT`
- Deserialization benchmark is in [DeserializationBenchmark.scala](src/main/scala/benchmarks/DeserializationBenchmark.scala). To run it use `jmh:run -i 5 -wi 5 -f1 -t1 .*DeserializationBenchmark*` in `SBT`

### Results
#### MacBook Pro (Retina, 13-inch, Early 2015):
- OS: macOS Sierra 10.12.6
- CPU: Intel Core i7 3.1 GHz
- Memory: 16 GB
- JVM: Java HotSpot(TM) 64-Bit Server VM, 25.181-b13

##### Serialization
| Library   | Time, ms|
| ----------| -------:|
| jsoniter  |  50.113 |
| circe     | 140.868 |
| spray     | 165.045 |
| lift      | 206.377 |
| play-json | 261.774 |
| Argonaut  | 306.668 |
| json4s    | 385.766 |

##### Deserialization
| Library   | Time, ms|
| ----------| -------:|
| jsoniter  | 78.070  |
| circe     | 130.938 |
| spray     | 180.159 |
| lift      | 220.421 |
| Argonaut  | 319.450 |
| play-json | 357.515 |
| json4s    | 467.872 |

#### Desktop machine:
- OS: Microsoft Windows 10 Pro N  x64 [Version 10.0.17134.590]
- CPU: AMD Ryzen 7 2700X Eight-Core Processor i7 3.7 GHz
- Memory: DDR4-3200 GHz 16 GB
- JVM: Java HotSpot(TM) 64-Bit Server VM, 25.201-b09

##### Serialization
| Library   | Time, ms|
| ----------| -------:|
| jsoniter  |  41.140 |
| circe     | 104.434 |
| spray     | 132.525 |
| lift      | 171.015 |
| play-json | 215.128 |
| Argonaut  | 255.371 |
| json4s    | 310.488 |

##### Deserialization
| Library   | Time, ms|
| ----------| -------:|
| jsoniter  | 68.833  |
| circe     | 117.592 |
| spray     | 155.961 |
| lift      | 220.304 |
| Argonaut  | 264.283 |
| play-json | 342.450 |
| json4s    | 403.946 |

