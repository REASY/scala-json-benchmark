package marshallers

import models.Bird
import org.json4s.jackson._
import org.json4s.{DefaultFormats, Formats}

class Json4SMarshaller extends Marshaller {
  def parse(s: String): Bird = {
    implicit val formats: Formats = DefaultFormats
    JsonMethods.parse(s).extract[Bird]
  }

  def toStr(bird: Bird): String = {
    implicit val formats: Formats = DefaultFormats
    Serialization.write(bird)
  }
}