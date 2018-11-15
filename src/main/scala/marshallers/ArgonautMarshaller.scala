package marshallers

import argonaut.Argonaut._
import argonaut._
import models.{Bird, Place}

class ArgonautMarshaller extends Marshaller {
  @transient implicit val placeCodecJson: CodecJson[Place] =
    casecodec5(Place.apply, Place.unapply)("name", "_id", "latlon", "description", "michelin_rate")

  @transient implicit val birdCodecJson: CodecJson[Bird] =
    casecodec5(Bird.apply, Bird.unapply)("scientific_name", "common_names", "sights", "wing_span", "hangs_out")

  def parse(s: String): Bird = {
    Parse.decodeOption[Bird](s).get
  }

  def toStr(bird: Bird): String = {
    bird.asJson.toString
  }
}