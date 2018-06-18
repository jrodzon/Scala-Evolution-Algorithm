package actors

import akka.actor.Actor
import evolution.CirclesProcesser.mutateGen

class MutateActor extends Actor {
  def receive = {
    case Message(list) =>
      val partOfGen = mutateGen(list)
      sender ! partOfGen
    case _ => println("Unexpected message")
  }
}