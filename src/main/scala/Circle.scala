import java.awt.Graphics2D
import java.awt.Color

class Circle(xCircle: Int, yCircle: Int, radius: Int)
  extends Shape(xCircle, yCircle, radius, radius) {

  override def draw(g: Graphics2D) {
    g.fillOval(x, y, width, height)
  }

}