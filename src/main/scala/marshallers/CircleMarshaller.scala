package marshallers

import io.circe._
import io.circe.generic.semiauto._
import io.circe.syntax._
import models.{Bird, Place}

class CircleMarshaller extends Marshaller {
  @transient implicit lazy val placeDecoder: Decoder[Place] = deriveDecoder[Place]
  @transient implicit lazy val birdDecoder: Decoder[Bird] = deriveDecoder[Bird]
  @transient implicit lazy val placeEncoder: Encoder[Place] = deriveEncoder[Place]
  @transient implicit lazy val birdEncoder: Encoder[Bird] = deriveEncoder[Bird]

  def parse(s: String): Bird = {
    io.circe.parser.parse(s).right.get.as[Bird].right.get
  }

  def toStr(bird: Bird): String = {
    bird.asJson.toString
  }
}