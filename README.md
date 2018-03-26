# A Scala JSON serialization/deserialization benchmark

### Introduction
When decision about JSON library should be made because of performance, this benchmark can be helpful. Benchmarked libraries:
- [Argonaut](https://github.com/argonaut-io/argonaut)
- [circe](https://github.com/circe/circe)
- [JSON4S](https://github.com/json4s/json4s)
- [Lift](https://github.com/lift/framework/tree/master/core/json)
- [spray-json](https://github.com/spray/spray-json)
- [play-json](https://github.com/playframework/play-json)

This project also demonstrates how to use these different JSON libraries to serialize/deserialize a Scala `case class`.

### Benchmark
Dataset and types `Bird` and `Place` from this repo: [scala-json-benchmark](https://github.com/nlw0/scala-json-benchmark). For benchmark used [ScalaMeter](https://scalameter.github.io/). File [birds.data](src/test/resources/birds.data) contains 25000 lines. Each of line is serialized json of `Bird`.
- Serialization benchmark is in [ToStrBenchmark.scala](src/test/scala/ToStrBenchmark.scala). To run it use `testOnly ToStrBenchmark` in `SBT`
- Deserialization benchmark is in [ParserBenchmark.scala](src/test/scala/ParserBenchmark.scala). To run it use `testOnly ParserBenchmark` in `SBT`

### Results
My machine is MacBook Pro (Retina, 13-inch, Early 2015):
- OS: macOS Sierra 10.12.6
- CPU: Intel Core i7 3.1 GHz
- Memory: 16 GB
- JVM: Java HotSpot(TM) 64-Bit Server VM v25.152-b16

##### Serialization
| Library   | Time, ms|
| ----------| -------:|
| circle    | 150.1   |
| spray     | 201.94  |
| Argonaut  | 257.26  |
| lift      | 267.52  |
| play-json | 293.95  |
| json4s    | 475.61  |

##### Deserialization
| Library   | Time, ms|
| ----------| -------:|
| circle    | 209.77  |
| spray     | 237.59  |
| lift      | 408.49  |
| Argonaut  | 433.61  |
| play-json | 604.79  |
| json4s    | 709     |

