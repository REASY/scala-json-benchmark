package marshallers

import models.Bird

trait Marshaller extends {
  def parse(s: String): Bird
  def toStr(bird: Bird): String
}