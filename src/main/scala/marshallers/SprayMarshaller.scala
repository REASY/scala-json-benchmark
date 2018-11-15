package marshallers

import models.{Bird, Place}
import spray.json.DefaultJsonProtocol._
import spray.json._

class SprayMarshaller extends Marshaller {
  def parse(s: String): Bird = {
    implicit val placeFormat: JsonFormat[Place] = jsonFormat5(Place)
    implicit val birdFormat: JsonFormat[Bird] = jsonFormat5(Bird)
    s.parseJson.convertTo[Bird]
  }

  def toStr(bird: Bird): String = {
    implicit val placeFormat: JsonFormat[Place] = jsonFormat5(Place)
    implicit val birdFormat: JsonFormat[Bird] = jsonFormat5(Bird)
    bird.toJson.toString()
  }
}
