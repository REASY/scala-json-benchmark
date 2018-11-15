import marshallers._
import models.Bird
import org.scalameter.Bench.ForkedTime
import org.scalameter.Gen
import org.scalameter.picklers.noPickler._

import scala.io.Source

case class ToStrBenchmarkData
(
  data: Array[Bird],
  len: Long
)

object ToStrBenchmark extends ForkedTime {
  def genData(birds: Array[Bird]): Gen[ToStrBenchmarkData] = {
    Gen.single("ToStrBenchmark")(ToStrBenchmarkData(birds, birds.length))
  }

  performance of "Json4SMarshaller" in {
    measureToStr("Json4SMarshaller")
  }

  performance of "LiftMarshaller" in {
    measureToStr("LiftMarshaller")
  }

  performance of "PlayMarshaller" in {
    measureToStr("PlayMarshaller")
  }

  performance of "ArgonautMarshaller" in {
    measureToStr("ArgonautMarshaller")
  }

  performance of "SprayMarshaller" in {
    measureToStr("SprayMarshaller")
  }

  performance of "CircleMarshaller" in {
    measureToStr("CircleMarshaller")
  }

  private def measureToStr(parserType: String): Unit = {
    val tempParser = new CircleMarshaller
    val birds: Array[Bird] = Source.fromFile(getClass.getResource("birds.data").getFile)
      .getLines().take(1).toArray
      .map { jsonStr => tempParser.parse(jsonStr) }

    val parser = parserType match {
      case "CircleMarshaller" => new CircleMarshaller
      case "SprayMarshaller" => new SprayMarshaller
      case "ArgonautMarshaller" => new ArgonautMarshaller
      case "PlayMarshaller" => new PlayMarshaller
      case "LiftMarshaller" => new LiftMarshaller
      case "Json4SMarshaller" => new Json4SMarshaller
    }

    measure.method("toStr") in {
      using(genData(birds)) in { pb =>
        pb.data.map(parser.toStr)
      }
    }
  }
}
