import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

object AkkaManager extends App {
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