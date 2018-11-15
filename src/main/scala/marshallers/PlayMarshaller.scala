package marshallers

import models.{Bird, Place}
import play.api.libs.json._

class PlayMarshaller extends Marshaller {
  @transient implicit lazy val placeReads: Reads[Place] = Json.reads[Place]
  @transient implicit lazy val birdReads: Reads[Bird] = Json.reads[Bird]

  @transient implicit lazy val placeWrites: Writes[Place] = Json.writes[Place]
  @transient implicit lazy val birdWrites: Writes[Bird] = Json.writes[Bird]

  def parse(s: String): Bird = {
    Json.parse(s).as[Bird]
  }

  def toStr(bird: Bird): String = {
    Json.toJson(bird).toString()
  }
}