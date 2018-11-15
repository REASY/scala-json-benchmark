package marshallers

import io.circe._
import io.circe.generic.semiauto._
import io.circe.syntax._
import models.{Bird, Place}

class CircleMarshaller extends Marshaller {
  def parse(s: String): Bird = {
    implicit val placeDecoder: Decoder[Place] = deriveDecoder[Place]
    implicit val birdDecoder: Decoder[Bird] = deriveDecoder[Bird]
    io.circe.parser.parse(s).right.get.as[Bird].right.get
  }

  def toStr(bird: Bird): String = {
    implicit val placeEncoder: Encoder[Place] = deriveEncoder[Place]
    implicit val birdEncoder: Encoder[Bird] = deriveEncoder[Bird]
    bird.asJson.toString
  }
}