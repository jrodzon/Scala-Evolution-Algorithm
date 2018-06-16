package actors

import akka.actor.{Actor, Props}
import evolution.ThrowPower
import presentation.SampleGUI

object Drawer {

  def props(message: String, throws: List[ThrowPower]): Props = Props(new Drawer(message, throws))

  case object Start
  case object Stop
}

class Drawer(message: String, throws: List[ThrowPower]) extends Actor {
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