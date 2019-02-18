package marshallers

import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import models.Bird

class JsoniterMarshaller extends Marshaller {
  implicit val birdCodec: JsonValueCodec[Bird] = JsonCodecMaker.make[Bird](CodecMakerConfig())

  def parse(s: String): Bird = {
    readFromString(s)
  }

  def toStr(bird: Bird): String = {
    writeToString(bird)
  }
}