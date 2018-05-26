import scala.collection.mutable.ArrayBuffer
import java.awt.Dimension

import javax.swing._
import java.awt.event._

import scala.swing.Panel
import java.awt.Color
import java.awt.Graphics2D

class ShapePanel() extends Panel {

  val width = 800
  val height = 400
  preferredSize = new Dimension(width, height)
  opaque = true
  background = Color.white

  override def paint(g: Graphics2D) {
    g.clearRect(0, 0, width, height)

    for(s <- shapes) {
      g.setColor(s.getColor())
      s.draw(g)
    }
  }

  var shapes = new ArrayBuffer[Shape]

  new Timer(10, new ActionListener {
    def actionPerformed(e: ActionEvent) {
      for(s <- shapes) { s.move(width, height) }
      repaint
    }
  }).start

  def addShape(shape: Shape) {
    shapes.append(shape)
  }
}