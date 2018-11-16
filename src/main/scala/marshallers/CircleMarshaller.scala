package marshallers

import io.circe._
import io.circe.generic.semiauto._
import io.circe.syntax._
import models.{Bird, Place}

class CircleMarshaller extends Marshaller {
  implicit val placeDecoder: Decoder[Place] = deriveDecoder[Place]
  implicit val birdDecoder: Decoder[Bird] = deriveDecoder[Bird]
  implicit val placeEncoder: Encoder[Place] = deriveEncoder[Place]
  implicit val birdEncoder: Encoder[Bird] = deriveEncoder[Bird]

  def parse(s: String): Bird = {
    io.circe.parser.parse(s).right.get.as[Bird].right.get
  }

  def toStr(bird: Bird): String = {
    bird.asJson.toString
  }
}