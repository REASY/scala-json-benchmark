package marshallers

import models.{Bird, Place}
import spray.json.DefaultJsonProtocol._
import spray.json._

class SprayMarshaller extends Marshaller {
  implicit val placeFormat: JsonFormat[Place] = jsonFormat5(Place)
  implicit val birdFormat: JsonFormat[Bird] = jsonFormat5(Bird)

  def parse(s: String): Bird = {
    s.parseJson.convertTo[Bird]
  }

  def toStr(bird: Bird): String = {
    bird.toJson.toString()
  }
}
