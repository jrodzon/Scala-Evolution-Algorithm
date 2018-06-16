package evolution

import configuration.Configuration

import scala.collection.mutable.ArrayBuffer
import scala.util.Random


object CirclesProcesser{

  def generateFirstGen(populationSize: Int): ArrayBuffer[ThrowPower] = {
    var circlesBuffer = new ArrayBuffer[ThrowPower]
      for (i <- 1 to populationSize) {
        circlesBuffer += ThrowPower(Random.nextDouble() * Configuration.maxXandY,
          Random.nextDouble() * Configuration.maxXandY, Configuration.maxFit)
      }
    circlesBuffer
  }


  def mutateGen(bestThrows: List[ThrowPower]): ArrayBuffer[ThrowPower] = {
    var newGeneration = new ArrayBuffer[ThrowPower]
    for (particularThrow <- bestThrows){
      for (i <- 1 to 6){
        newGeneration += ThrowPower(particularThrow.speedX + Random.nextDouble() - 0.5,
          particularThrow.speedY + Random.nextDouble() - 0.5, Configuration.maxFit)
      }
    }
    newGeneration
  }

  def crossGen(bestThrows: List[ThrowPower]): ArrayBuffer[ThrowPower] = {

    def crossTwo(throw1: ThrowPower, throw2: ThrowPower): ThrowPower = {
      val newX = (throw1.speedX + throw2.speedX) / 2
      val newY = (throw1.speedY + throw2.speedY) / 2
      ThrowPower(newX, newY, Configuration.maxFit)
    }

    Random.shuffle(bestThrows)
    var crossedSpecies = new ArrayBuffer[ThrowPower]

    for (i <- 0 until (bestThrows.length / 2) by 2) {
      crossedSpecies += crossTwo(bestThrows(i), bestThrows(i+1))
    }

    crossedSpecies
  }

  val throws = generateFirstGen(30).toList


  for (t <- throws) {
    t.calculateFitness(Configuration.targetX)
    println(t.fitness)
  }
  println()
  println()

  def takeBest(throws: List[ThrowPower], quantity: Int): List[ThrowPower] = {
    val array : List[ThrowPower] = throws.sortBy(_.fitness).take(quantity)
    array
  }

  val throws1 = mutateGen(takeBest(throws, 3))

  for (t <- throws1) {
    t.calculateFitness(Configuration.targetX)
    println(t.fitness)
  }

  val throws2 = mutateGen(takeBest(throws1.toList, 3))

  for (t <- throws1) {
    t.calculateFitness(Configuration.targetX)
    println(t.fitness)
  }
}
