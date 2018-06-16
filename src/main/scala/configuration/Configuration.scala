package configuration

object Configuration {
  //presentation parameters
  val width = 1200
  val height = 600
  val groundSkyBorder : Int = height*3/4
  val circleRadius = 50
  val xCircleStart : Int = 2*circleRadius
  val yCircleStart : Int = groundSkyBorder - circleRadius
  val gravitation : Double = 1.0
  val gravitationFrequency : Int = 10
  val coefficientOfElasticity : Double = 0.6
  val minimalYSpeed : Double = 1.1

  //evolution-algorithm parameters
  val maxFit = 1000
  val targetX = 1200
  val maxXandY = 30
  val populationSize = 30
}
