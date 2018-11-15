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
  @transient private val tempParser = new CircleMarshaller
  @transient private val birds: Array[Bird] = Source.fromFile(getClass.getResource("birds.data").getFile)
    .getLines().toArray
    .map { jsonStr => tempParser.parse(jsonStr) }
  def genData: Gen[ToStrBenchmarkData] = Gen.single("ToStrBenchmark")(ToStrBenchmarkData(birds, birds.length))

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

  performance of "CircleMarshaller" in {
    measureToStr(new CircleMarshaller)
  }

  private def measureToStr(marshaller: Marshaller): Unit = {
    measure.method("toStr") in {
      using(genData) in { pb =>
        pb.data.map(marshaller.toStr)
      }
    }
  }
}
