import org.scalameter.api._
import org.scalameter.picklers.noPickler._
import org.scalameter.{Gen, Reporter}
import marshallers._

import scala.io.Source

case class ParserBenchmarkData
(
  data: Array[String],
  len: Long
)

object ParserBenchmark extends Bench[Double]{
  val data: Array[String] = Source.fromFile(getClass.getResource("birds.data").getFile)
    .getLines()
    .toArray
  def genData: Gen[ParserBenchmarkData] = Gen.single("ParserBenchmark")(ParserBenchmarkData(data, data.length))

  lazy val executor = LocalExecutor(new Executor.Warmer.Default, Aggregator.average, measurer)
  lazy val reporter: Reporter[Double] = new LoggingReporter[Double]
  lazy val persistor: Persistor = Persistor.None
  lazy val measurer: Measurer[Double] = new Measurer.Default

  performance of "CircleMarshaller" in {
    measureParse(new CircleMarshaller)
  }

  performance of "Json4SMarshaller" in {
    measureParse(new Json4SMarshaller)
  }

  performance of "LiftMarshaller" in {
    measureParse(new LiftMarshaller)
  }

  performance of "PlayMarshaller" in {
    measureParse(new PlayMarshaller)
  }

  performance of "ArgonautMarshaller" in {
    measureParse(new ArgonautMarshaller)
  }

  performance of "SprayMarshaller" in {
    measureParse(new SprayMarshaller)
  }

  private def measureParse(parser: Marshaller): Unit = {
    measure.method("parse") in {
      using(genData) in { pb =>
        pb.data.map(parser.parse)
      }
    }
  }
}
