package evolution

import configuration.Configuration

case class ThrowPower(speedX: Double, var speedY: Double, var fitness: Double){

  def landingDiff(): Unit = {
    val gravity: Double = Configuration.gravitation / Configuration.gravitationFrequency.toDouble
    val landingX = (4 * speedY * speedX) / gravity - Configuration.xCircleStart
    println(speedX + " " + speedY + " " + landingX)

    fitness = math.abs(Configuration.targetX - landingX)
  }

}
