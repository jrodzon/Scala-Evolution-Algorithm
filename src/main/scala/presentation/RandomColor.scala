package presentation

import java.awt.Color

import scala.util.Random

object RandomColor {
  val colors = Array(Color.CYAN, Color.BLACK, Color.DARK_GRAY, Color.GRAY, Color.MAGENTA, Color.LIGHT_GRAY, Color.PINK, Color.RED, Color.ORANGE, Color.YELLOW);
  val numberOfColors : Int = colors.length

  def getNewColor() : Color = {
    colors(Random.nextInt(numberOfColors))
  }

}
