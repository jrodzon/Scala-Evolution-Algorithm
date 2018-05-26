import scala.swing._
import event._
import java.awt.Color

class SampleGUI extends SimpleSwingApplication {

  var circles : Circle = _

  def setCircles(c : Circle) = {
    circles = c
  }

  def top = new MainFrame {
    title = "A Simple Scala Swing GUI"

    val shapePanel = new ShapePanel()

    shapePanel.addShape(circles)

    contents = shapePanel
  }
}