package actors

import akka.actor.Actor
import evolution.CirclesProcesser.crossGen

class CrossActor extends Actor {
  def receive = {
    case Message(list) =>
      val partOfGen = crossGen(list)
      sender ! partOfGen
    case _ => println("Unexpected message")
  }
}