package marshallers

import models.Bird

trait Marshaller {
  def parse(s: String): Bird
  def toStr(bird: Bird): String
}