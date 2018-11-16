package marshallers

import models.{Bird, Place}
import play.api.libs.json._

class PlayMarshaller extends Marshaller {
  implicit val placeReads: Reads[Place] = Json.reads[Place]
  implicit val birdReads: Reads[Bird] = Json.reads[Bird]

  implicit val placeWrites: Writes[Place] = Json.writes[Place]
  implicit val birdWrites: Writes[Bird] = Json.writes[Bird]

  def parse(s: String): Bird = {
    Json.parse(s).as[Bird]
  }

  def toStr(bird: Bird): String = {
    Json.toJson(bird).toString()
  }
}