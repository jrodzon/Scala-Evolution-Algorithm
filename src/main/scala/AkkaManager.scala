import actors.Drawer
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import evolution.CirclesProcesser._

object AkkaManager extends App {
  import actors.Drawer._

  // Create the 'actorFactory' actor system
  val system: ActorSystem = ActorSystem("actorFactory")

  val firstGen = generateFirstGen(500)

  //#create-actors
  val drawer: ActorRef = system.actorOf(Drawer.props("Drawer", firstGen.toList))
  //#create-actors

  //#main-send-messages
  drawer ! Start
  //#main-send-messages
}