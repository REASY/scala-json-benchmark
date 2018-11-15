import marshallers._
import org.scalameter.Bench.ForkedTime
import org.scalameter.Gen
import org.scalameter.picklers.noPickler._

import scala.io.Source

case class ParserBenchmarkData
(
  data: Array[String],
  len: Long
)

object ParserBenchmark extends ForkedTime{
  def genData(data: Array[String]): Gen[ParserBenchmarkData] = {
    Gen.single("ParserBenchmark")(ParserBenchmarkData(data, data.length))
  }

  performance of "Json4SMarshaller" in {
    measureParse("Json4SMarshaller")
  }

  performance of "LiftMarshaller" in {
    measureParse("LiftMarshaller")
  }

  performance of "PlayMarshaller" in {
    measureParse("PlayMarshaller")
  }

  performance of "ArgonautMarshaller" in {
    measureParse("ArgonautMarshaller")
  }

  performance of "SprayMarshaller" in {
    measureParse("SprayMarshaller")
  }

  performance of "CircleMarshaller" in {
    measureParse("CircleMarshaller")
  }

  private def measureParse(parserType: String): Unit = {
    val parser = parserType match {
      case "CircleMarshaller" => new CircleMarshaller
      case "SprayMarshaller" => new SprayMarshaller
      case "ArgonautMarshaller" => new ArgonautMarshaller
      case "PlayMarshaller" => new PlayMarshaller
      case "LiftMarshaller" => new LiftMarshaller
      case "Json4SMarshaller" => new Json4SMarshaller
    }
    val data: Array[String] = Source.fromFile(getClass.getResource("birds.data").getFile)
      .getLines()
      .take(1)
      .toArray
    measure.method("parse") in {
      using(genData(data)) in { pb =>
        pb.data.map(parser.parse)
      }
    }
  }
}
