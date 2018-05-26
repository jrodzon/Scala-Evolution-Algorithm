import java.awt.{Color, Graphics2D}

abstract class Shape(var x: Int, var y:Int, var width:Int, var height:Int) {

  protected var xSpeed = 3
  protected var ySpeed = 3

  protected val color : Color = RandomColor.getNewColor()

  def draw(g: Graphics2D) {
    g.fillRect(x, y, width, height)
  }

  def getColor(): Color ={
    color
  }

  def move(panelWidth: Int, panelHeight: Int) {
    if (x >= panelWidth - this.width || x <= 0) {
      xSpeed *= -1;
    }
    if (y >= panelHeight - this.height || y <= 0) {
      ySpeed *= -1;
    }

    x += xSpeed;
    y += ySpeed;
  }
}