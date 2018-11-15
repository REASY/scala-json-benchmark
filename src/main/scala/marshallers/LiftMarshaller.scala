package marshallers

import models.Bird
import net.liftweb.json.Formats
import net.liftweb.json.Serialization.{read, write}

class LiftMarshaller extends Marshaller {
  @transient implicit val formats: Formats = net.liftweb.json.DefaultFormats

  def parse(s: String): Bird = {
    read[Bird](s)
  }

  def toStr(bird: Bird): String = {
    write(bird)
  }
}