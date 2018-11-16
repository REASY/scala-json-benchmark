package benchmarks

import java.nio.charset.StandardCharsets

import marshallers._
import models.Bird

import scala.io.Source
import org.openjdk.jmh.annotations._
import java.util.concurrent.TimeUnit

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
class SerializationBenchmark{
  import SerializationBenchmark._

  @Benchmark
  def Json4SMarshaller_toStr(): Int = {
    val parser = new Json4SMarshaller
    val strs = birds.map(parser.toStr)
    strs.length
  }

  @Benchmark
  def LiftMarshaller_toStr(): Int = {
    val parser = new LiftMarshaller
    val strs = birds.map(parser.toStr)
    strs.length
  }

  @Benchmark
  def PlayMarshaller_toStr(): Int = {
    val parser = new PlayMarshaller
    val strs = birds.map(parser.toStr)
    strs.length
  }

  @Benchmark
  def ArgonautMarshaller_toStr(): Int = {
    val parser = new ArgonautMarshaller
    val strs = birds.map(parser.toStr)
    strs.length
  }

  @Benchmark
  def SprayMarshaller_toStr(): Int = {
    val parser = new SprayMarshaller
    val strs = birds.map(parser.toStr)
    strs.length
  }

  @Benchmark
  def CircleMarshaller_toStr(): Int = {
    val parser = new CircleMarshaller
    val strs = birds.map(parser.toStr)
    strs.length
  }
}
object SerializationBenchmark {
  val tempParser = new CircleMarshaller
  val birds: Array[Bird] = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("birds.data"), StandardCharsets.UTF_8.name())
    .getLines().toArray
    .map { jsonStr => tempParser.parse(jsonStr) }
}