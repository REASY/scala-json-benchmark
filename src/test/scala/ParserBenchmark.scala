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
  @transient private val data: Array[String] = Source.fromFile(getClass.getResource("birds.data").getFile)
    .getLines()
    .toArray
  def genData: Gen[ParserBenchmarkData] = Gen.single("ParserBenchmark")(ParserBenchmarkData(data, data.length))

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

  performance of "CircleMarshaller" in {
    measureParse(new CircleMarshaller)
  }

  private def measureParse(parser: Marshaller): Unit = {
    measure.method("parse") in {
      using(genData) in { pb =>
        pb.data.map(parser.parse)
      }
    }
  }
}
