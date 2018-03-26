import models.Bird
import org.scalameter.api._
import org.scalameter.picklers.noPickler._
import org.scalameter.{Gen, Reporter}
import marshallers._

import scala.io.Source

case class ToStrBenchmarkData
(
  data: Array[Bird],
  len: Long
)

object ToStrBenchmark extends Bench[Double]{
  val tempParser = new CircleMarshaller
  val birds: Array[Bird] = Source.fromFile(getClass.getResource("birds.data").getFile)
    .getLines().toArray
    .map { jsonStr => tempParser.parse(jsonStr) }
  def genData: Gen[ToStrBenchmarkData] = Gen.single("ToStrBenchmark")(ToStrBenchmarkData(birds, birds.length))

  lazy val executor = LocalExecutor(new Executor.Warmer.Default, Aggregator.average, measurer)
  lazy val reporter: Reporter[Double] = new LoggingReporter[Double]
  lazy val persistor: Persistor = Persistor.None
  lazy val measurer: Measurer[Double] = new Measurer.Default

  performance of "CircleMarshaller" in {
    measureToStr(new CircleMarshaller)
  }

  performance of "Json4SMarshaller" in {
    measureToStr(new Json4SMarshaller)
  }

  performance of "LiftMarshaller" in {
    measureToStr(new LiftMarshaller)
  }

  performance of "PlayMarshaller" in {
    measureToStr(new PlayMarshaller)
  }

  performance of "ArgonautMarshaller" in {
    measureToStr(new ArgonautMarshaller)
  }

  performance of "SprayMarshaller" in {
    measureToStr(new SprayMarshaller)
  }

  private def measureToStr(marshaller: Marshaller): Unit = {
    measure.method("toStr") in {
      using(genData) in { pb =>
        pb.data.map(marshaller.toStr)
      }
    }
  }
}
