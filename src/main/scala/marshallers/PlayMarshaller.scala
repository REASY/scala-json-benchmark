package marshallers

import models.{Bird, Place}
import play.api.libs.json._


class PlayMarshaller extends Marshaller {
  @transient implicit val placeReads: Reads[Place] = Json.reads[Place]
  @transient implicit val birdReads: Reads[Bird] = Json.reads[Bird]

  @transient implicit val placeWrites: Writes[Place] = Json.writes[Place]
  @transient implicit val birdWrites: Writes[Bird] = Json.writes[Bird]

  def parse(s: String): Bird = {
    Json.parse(s).as[Bird]
  }

  def toStr(bird: Bird): String = {
    Json.toJson(bird).toString()
  }
}