package actors

import akka.actor.Actor
import evolution.CirclesProcesser.getParents

class ParentActor extends Actor {
  def receive = {
    case Message(list) =>
      val partOfGen = getParents(list)
      sender ! partOfGen
    case _ => println("Unexpected message")
  }
}