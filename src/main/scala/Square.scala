import java.awt.Graphics2D

class Square(xSquare: Int, ySquare: Int, len: Int)
  extends Shape(xSquare, ySquare, len, len) {

  override def draw(g: Graphics2D) {
    g.fillRect(x, y, width, height)
  }
}