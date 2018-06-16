package presentation

import java.awt.{Color, Dimension, Graphics2D}
import java.awt.event._

import configuration.Configuration
import javax.swing._
import presentation.shapes.{Movable, Shape}

import scala.collection.mutable.ArrayBuffer
import scala.swing.Panel

class ShapePanel(width : Int, height : Int) extends Panel {

  preferredSize = new Dimension(width, height)
  opaque = true
  background = Color.white

  override def paint(g: Graphics2D) {
    g.clearRect(0, 0, width, height)

    for(s <- shapes) {
      g.setColor(s.color)
      s.draw(g)
    }
  }

  var shapes = new ArrayBuffer[Shape]

  new Timer(Configuration.timeBetweenFrames, new ActionListener {
    def actionPerformed(e: ActionEvent) {
      for(s <- shapes) {
        s match {
          case _ : Movable => s.asInstanceOf[Movable].move(width, height)
          case _ => Unit
        }
      }
      repaint
    }
  }).start

  def addShape(shape: Shape) {
    shapes.append(shape)
  }
}