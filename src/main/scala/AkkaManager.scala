import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

object Drawer {

  def props(message: String, circles: Circle): Props = Props(new Drawer(message, circles))

  case object Start
  case object Stop
}
//#greeter-messages
//#greeter-companion

//#greeter-actor
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
//#greeter-actor

//#printer-companion
//#printer-messages
object Printer {
  //#printer-messages
  def props: Props = Props[Printer]
  //#printer-messages
  final case class Greeting(greeting: String)
}
//#printer-messages
//#printer-companion

//#printer-actor
class Printer extends Actor with ActorLogging {
  import Printer._

  def receive = {
    case Greeting(greeting) =>
      log.info(s"Greeting received (from ${sender()}): $greeting")
  }
}
//#printer-actor

//#main-class
object AkkaQuickstart extends App {
  import Drawer._

  // Create the 'helloAkka' actor system
  val system: ActorSystem = ActorSystem("drawerAkka")

  //#create-actors
  val drawer: ActorRef = system.actorOf(Drawer.props("Drawer", new Circle(40, 40, 50)))
  //#create-actors

  //#main-send-messages
  drawer ! Start
  //#main-send-messages
}
//#main-class
//#full-example