package models

case class Place
(
  name: String,
  _id: Int,
  latlon: List[Double],
  description: Option[String],
  michelin_rate: Option[Int]
)
