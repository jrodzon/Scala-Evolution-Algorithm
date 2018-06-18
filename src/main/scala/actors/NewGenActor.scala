package actors

import akka.actor.Actor
import configuration.Configuration
import evolution.CirclesProcesser.generateFirstGen

class NewGenActor extends Actor {
  def receive = {
    case Message(list) =>
      val partOfGen = generateFirstGen(Configuration.populationSize/5)
      sender ! partOfGen
    case _ => println("Unexpected message")
  }
}