package actors

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import configuration.Configuration
import evolution.ThrowPower

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._


object Generator {
  def generateNextGeneration(throws: List[ThrowPower]): List[ThrowPower] = {
    val system = ActorSystem("GeneratorSystem")
    val myParentActor = system.actorOf(Props[ParentActor], name = "myParentActor")
    val myMutateActor = system.actorOf(Props[MutateActor], name = "myMutateActor")
    val myCrossActor = system.actorOf(Props[CrossActor], name = "myCrossActor")
    val myNewGenActor = system.actorOf(Props[NewGenActor], name = "myNewGenActor")

    implicit val timeout: Timeout = Timeout(5 seconds)

    val futureParent: Future[List[ThrowPower]] = ask(myParentActor, Message(throws)).mapTo[List[ThrowPower]]
    val resultParent = Await.result(futureParent, 1 second)
    val futureMutate: Future[List[ThrowPower]] = ask(myMutateActor, Message(throws)).mapTo[List[ThrowPower]]
    val resultMutate = Await.result(futureMutate, 1 second)
    val futureCross: Future[List[ThrowPower]] = ask(myCrossActor, Message(throws)).mapTo[List[ThrowPower]]
    val resultCross = Await.result(futureCross, 1 second)
    val futureNewGen: Future[List[ThrowPower]] = ask(myNewGenActor, Message(throws)).mapTo[List[ThrowPower]]
    val resultNewGen = Await.result(futureNewGen, 1 second)

    val wholeGen: List[ThrowPower] = resultParent ++ resultMutate ++ resultCross ++ resultNewGen

    for (t <- wholeGen) {
      t.landingDiff()
    }

    val nextGen = wholeGen.toSet[ThrowPower].toList.sortBy(_.fitness).take(Configuration.populationSize)
    nextGen
  }
}



