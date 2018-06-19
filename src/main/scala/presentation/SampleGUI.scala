package presentation

import java.awt.Color

import configuration.Configuration
import evolution.ThrowPower
import presentation.shapes._

import scala.swing._

class SampleGUI extends SimpleSwingApplication {

  var circles : List[Circle] = _

  def setThrows(throws : List[ThrowPower]) : Unit = {
    circles = throws.map(t => new Circle(Configuration.xCircleStart, Configuration.yCircleStart, Configuration.circleRadius, t.speedX, -t.speedY))
  }

  def top = new MainFrame {
    title = "Evolution algorithm visualisation"

    val shapePanel = new ShapePanel(Configuration.width, Configuration.height)

    val grass = new shapes.Rectangle(0, Configuration.groundSkyBorder, Configuration.width, Configuration.groundSkyBorder, Color.GREEN)
    shapePanel.addShape(grass)

    val sky = new shapes.Rectangle(0, 0, Configuration.width, Configuration.groundSkyBorder, Color.BLUE)
    shapePanel.addShape(sky)

    circles.foreach(v => shapePanel.addShape(v))


    contents = shapePanel
  }
}