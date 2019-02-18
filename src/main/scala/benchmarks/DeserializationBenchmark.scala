package benchmarks

import java.nio.charset.StandardCharsets

import marshallers._

import scala.io.Source
import org.openjdk.jmh.annotations._
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class DeserializationBenchmark {
  import DeserializationBenchmark._

  @Benchmark
  def Json4SMarshaller_parse(): Int = {
    val parser = new Json4SMarshaller
    val parsed = data.map(parser.parse)
    parsed.length
  }

  @Benchmark
  def CircleMarshaller_parse(): Int = {
    val parser = new CircleMarshaller
    val parsed = data.map(parser.parse)
    parsed.length
  }

  @Benchmark
  def SprayMarshaller_parse(): Int = {
    val parser = new SprayMarshaller
    val parsed = data.map(parser.parse)
    parsed.length
  }

  @Benchmark
  def ArgonautMarshaller_parse(): Int = {
    val parser = new ArgonautMarshaller
    val parsed = data.map(parser.parse)
    parsed.length
  }

  @Benchmark
  def PlayMarshaller_parse(): Int = {
    val parser = new PlayMarshaller
    val parsed = data.map(parser.parse)
    parsed.length
  }

  @Benchmark
  def LiftMarshaller_parse(): Int = {
    val parser = new LiftMarshaller
    val parsed = data.map(parser.parse)
    parsed.length
  }

  @Benchmark
  def JsoniterMarshaller_parse(): Int = {
    val parser = new JsoniterMarshaller
    val parsed = data.map(parser.parse)
    parsed.length
  }
}

object DeserializationBenchmark {
  val data: Array[String] = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("birds.data"), StandardCharsets.UTF_8.name())
    .getLines()
    .toArray
}
