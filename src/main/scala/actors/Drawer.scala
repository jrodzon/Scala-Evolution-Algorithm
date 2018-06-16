package actors

import akka.actor.{Actor, Props}
import presentation.SampleGUI

object Drawer {

  def props(message: String, throws: List[Tuple2[Double, Double]]): Props = Props(new Drawer(message, throws))

  case object Start
  case object Stop
}

class Drawer(message: String, throws: List[Tuple2[Double, Double]]) extends Actor {
  import Drawer._

  def receive = {
    case Start =>
      val gui = new SampleGUI
      gui.setThrows(throws)
      gui.startup(null)
    case Stop =>
      context.stop(self)
  }
}