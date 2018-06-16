import actors.Drawer
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import configuration.Configuration
import evolution.CirclesProcesser._

object AkkaManager extends App {
  import actors.Drawer._

  // Create the 'actorFactory' actor system
  val system: ActorSystem = ActorSystem("actorFactory")

  val firstGen = generateFirstGen(Configuration.populationSize)

  var currentGen = generateFirstGen(Configuration.populationSize)
  for (i <- 1 to 10){
    val nextGen = generateNextGeneration(currentGen)
    currentGen = nextGen
  }

  //sys.exit(1)
  //#create-actors
  val drawer: ActorRef = system.actorOf(Drawer.props("Drawer", currentGen))
  //#create-actors

  //#main-send-messages
  drawer ! Start
  //#main-send-messages
}