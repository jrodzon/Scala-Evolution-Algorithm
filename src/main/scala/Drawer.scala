import akka.actor.{Actor, Props}

object Drawer {

  def props(message: String, circles: Circle): Props = Props(new Drawer(message, circles))

  case object Start
  case object Stop
}

class Drawer(message: String, circles: Circle) extends Actor {
  import Drawer._

  def receive = {
    case Start =>
      val gui = new SampleGUI
      gui.setCircles(circles)
      gui.startup(null)
    case Stop =>
      context.stop(self)
  }
}