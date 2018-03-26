package models

case class Bird
(
  scientific_name: String,
  common_names: List[String],
  sights: Int,
  wing_span: Double,
  hangs_out: List[Place]
)