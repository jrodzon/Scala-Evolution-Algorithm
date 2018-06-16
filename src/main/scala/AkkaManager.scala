import actors.Drawer
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

object AkkaManager extends App {
  import actors.Drawer._

  // Create the 'actorFactory' actor system
  val system: ActorSystem = ActorSystem("actorFactory")

  //#create-actors
  val drawer: ActorRef = system.actorOf(Drawer.props("Drawer", List((2.5, 18.3), (1.5, 5.4), (24.5, 18.3), (12.5, 5.4), (4.5, 18.3), (1.0, 5.4), (2.2, 1.3), (12.5, 5.4), (2.35, 148.3), (12.5, 5.4), (21.5, 18.3), (12.5, 15.4))))
  //#create-actors

  //#main-send-messages
  drawer ! Start
  //#main-send-messages
}