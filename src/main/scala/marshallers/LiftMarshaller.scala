package marshallers

import models.Bird
import net.liftweb.json.Formats
import net.liftweb.json.Serialization.{read, write}

class LiftMarshaller extends Marshaller {
  def parse(s: String): Bird = {
    implicit val formats: Formats = net.liftweb.json.DefaultFormats
    read[Bird](s)
  }

  def toStr(bird: Bird): String = {
    implicit val formats: Formats = net.liftweb.json.DefaultFormats
    write(bird)
  }
}