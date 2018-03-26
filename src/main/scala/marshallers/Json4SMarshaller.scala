package marshallers

import models.Bird
import org.json4s.jackson._
import org.json4s.{DefaultFormats, Formats}

class Json4SMarshaller extends Marshaller {
  implicit val formats: Formats = DefaultFormats

  def parse(s: String): Bird = {
    JsonMethods.parse(s).extract[Bird]
  }

  def toStr(bird: Bird): String = {
    Serialization.write(bird)
  }
}