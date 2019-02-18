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
Dataset and types `Bird` and `Place` from this repo: [scala-json-benchmark](https://github.com/nlw0/scala-json-benchmark). For benchmark used [jmh](https://openjdk.java.net/projects/code-tools/jmh/) via [sbt-jmh plugin](https://github.com/ktoso/sbt-jmh/). File [birds.data](src/main/resources/birds.data) contains 25000 lines. Each of line is serialized json of `Bird`.
- Serialization benchmark is in [SerializationBenchmark.scala](src/main/scala/benchmarks/SerializationBenchmark.scala). To run it use `jmh:run -i 5 -wi 5 -f1 -t1 .*SerializationBenchmark*` in `SBT`
- Deserialization benchmark is in [DeserializationBenchmark.scala](src/main/scala/benchmarks/DeserializationBenchmark.scala). To run it use `jmh:run -i 5 -wi 5 -f1 -t1 .*DeserializationBenchmark*` in `SBT`

### Results
My machine is MacBook Pro (Retina, 13-inch, Early 2015):
- OS: macOS Sierra 10.12.6
- CPU: Intel Core i7 3.1 GHz
- Memory: 16 GB
- JVM: Java HotSpot(TM) 64-Bit Server VM, 25.181-b13

##### Serialization
| Library   | Time, ms|
| ----------| -------:|
| jsoniter  |  50.113 |
| circle    | 140.868 |
| spray     | 165.045 |
| lift      | 206.377 |
| play-json | 261.774 |
| Argonaut  | 306.668 |
| json4s    | 385.766 |

##### Deserialization
| Library   | Time, ms|
| ----------| -------:|
| jsoniter  | 78.070  |
| circle    | 130.938 |
| spray     | 180.159 |
| lift      | 220.421 |
| Argonaut  | 319.450 |
| play-json | 357.515 |
| json4s    | 467.872 |

