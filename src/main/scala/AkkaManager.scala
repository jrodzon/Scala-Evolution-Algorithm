import actors.Drawer
import akka.actor.{ActorRef, ActorSystem}
import configuration.Configuration
import evolution.CirclesProcesser.generateFirstGen
import actors.Generator.generateNextGeneration

object AkkaManager extends App {
  import actors.Drawer._

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
}