package marshallers

import models.Bird

trait Marshaller extends Serializable {
  def parse(s: String): Bird
  def toStr(bird: Bird): String
}