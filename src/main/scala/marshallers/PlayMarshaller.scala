package marshallers

import models.{Bird, Place}
import play.api.libs.json._

class PlayMarshaller extends Marshaller {
  def parse(s: String): Bird = {
    implicit val placeReads: Reads[Place] = Json.reads[Place]
    implicit val birdReads: Reads[Bird] = Json.reads[Bird]
    Json.parse(s).as[Bird]
  }

  def toStr(bird: Bird): String = {
    implicit val placeWrites: Writes[Place] = Json.writes[Place]
    implicit val birdWrites: Writes[Bird] = Json.writes[Bird]
    Json.toJson(bird).toString()
  }
}