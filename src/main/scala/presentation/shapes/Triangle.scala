package presentation.shapes

import java.awt.{Graphics2D, Polygon}

class Triangle(xTriangle: Int, yTriangle: Int, wTriangle: Int, hTriangle: Int)
  extends Shape(xTriangle, yTriangle, wTriangle, hTriangle) {

  val numberSides = 3;
  val xpoints = new Array[Int](numberSides);
  val ypoints = new Array[Int](numberSides);
  val npoints = numberSides;

  override def draw(g: Graphics2D) {
    var pointNum = 0;

    xpoints(pointNum) = x;
    ypoints(pointNum) = y;
    pointNum += 1
    xpoints(pointNum) = x+width;
    ypoints(pointNum) = y;
    pointNum += 1
    xpoints(pointNum) = x+width/2;
    ypoints(pointNum) = y+height;

    val p = new Polygon(xpoints, ypoints, npoints);
    g.fillPolygon(p);
  }
}