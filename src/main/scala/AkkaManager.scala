import actors.Drawer
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import configuration.Configuration
import evolution.CirclesProcesser._

object AkkaManager extends App {
  import actors.Drawer._

  // Create the 'actorFactory' actor system
  val system: ActorSystem = ActorSystem("actorFactory")

  var currentGen = generateFirstGen(Configuration.populationSize)
  val drawer: ActorRef = system.actorOf(Drawer.props("Drawer", currentGen))
  drawer ! Start
  scala.io.StdIn.readLine()
  while (true){
    val nextGen = generateNextGeneration(currentGen)
    currentGen = nextGen
    val drawer: ActorRef = system.actorOf(Drawer.props("Drawer", currentGen))
    drawer ! Start
    val input = scala.io.StdIn.readLine()
    if(input.equals("q")){
      sys.exit()
    }
    system.stop(drawer)
  }

  sys.exit(1)
  //#create-actors
  val drawer1: ActorRef = system.actorOf(Drawer.props("Drawer", currentGen))
  //#create-actors

  //#main-send-messages
  drawer ! Start
  //#main-send-messages
}