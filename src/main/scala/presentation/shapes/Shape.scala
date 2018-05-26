package presentation.shapes

import java.awt.{Color, Graphics2D}

abstract class Shape(var x: Int, var y:Int, var width:Int, var height:Int, val color : Color = Color.BLACK) {

  def draw(g: Graphics2D) {
    g.fillRect(x, y, width, height)
  }
}