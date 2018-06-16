package presentation

import java.awt.Color

import configuration.Configuration
import presentation.shapes._

import scala.swing._

class SampleGUI extends SimpleSwingApplication {

  var circles : List[Circle] = _

  def setThrows(throws : List[Tuple2[Double, Double]]) : Unit = {
    circles = throws.map(t => new Circle(Configuration.xCircleStart, Configuration.yCircleStart, Configuration.circleRadius, t._1, -t._2))
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