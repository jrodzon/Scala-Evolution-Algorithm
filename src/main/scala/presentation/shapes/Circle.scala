package presentation.shapes

import java.awt.{Color, Graphics2D}

import configuration.Configuration
import presentation.RandomColor

class Circle (xCircle: Int, yCircle: Int, radius: Int, var xSpeed : Double, var ySpeed : Double)
  extends Shape(xCircle, yCircle, radius, radius)
    with Movable {

  override val color : Color = RandomColor.getNewColor()

  var currentXCoord : Double = xCircle.toDouble
  var currentYCoord : Double = yCircle.toDouble
  var noGravitationFrames : Int = 0

  override def draw(g: Graphics2D) {
    g.fillOval(currentXCoord.toInt, currentYCoord.toInt, radius, radius)
  }

  var stabilizeYSpeed : Boolean = false

  def move(panelWidth: Int, panelHeight: Int) {
    if(currentYCoord.toInt + ySpeed >= Configuration.groundSkyBorder - radius){
      if(ySpeed < Configuration.minimalYSpeed){
        stabilizeYSpeed = true
      }
      ySpeed = - ySpeed * Configuration.coefficientOfElasticity
    }
    if(noGravitationFrames >= Configuration.gravitationFrequency){
      noGravitationFrames = 0
      ySpeed += Configuration.gravitation
    }
    else{
      noGravitationFrames += 1
    }
    currentXCoord += xSpeed
    if(!stabilizeYSpeed){
      currentYCoord += ySpeed
    }
  }

}