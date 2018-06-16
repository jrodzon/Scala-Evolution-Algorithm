package evolution

import configuration.Configuration

import scala.collection.mutable.ArrayBuffer
import scala.util.Random


object CirclesProcesser{

  def generateFirstGen(populationSize: Int): List[ThrowPower] = {
    var circlesBuffer = new ArrayBuffer[ThrowPower]
      for (i <- 1 to populationSize) {
        circlesBuffer += ThrowPower(Random.nextDouble() * Configuration.maxXandY,
          Random.nextDouble() * Configuration.maxXandY + 1.0, Configuration.maxFit)
      }
    circlesBuffer.toList
  }


  def mutateGen(bestThrows: List[ThrowPower]): List[ThrowPower] = {
    var newGeneration = new ArrayBuffer[ThrowPower]
    for (particularThrow <- bestThrows){
        newGeneration += ThrowPower(particularThrow.speedX + (Random.nextDouble() - 0.5) * Configuration.maxXandY/10,
          particularThrow.speedY + (Random.nextDouble() - 0.5) * Configuration.maxXandY/10, Configuration.maxFit)
    }
    newGeneration.toList
  }

  def crossGen(bestThrows: List[ThrowPower]): List[ThrowPower] = {

    def crossTwo(throw1: ThrowPower, throw2: ThrowPower): ThrowPower = {
      val determinant: Int = Random.nextInt(7)
      val avgX = (throw1.speedX+throw2.speedX) / 2
      val avgY = (throw1.speedX+throw2.speedX) / 2
      determinant match {
        case 0 => ThrowPower(avgX, avgY, Configuration.maxFit)
        case 1 => ThrowPower(throw1.speedX, avgY, Configuration.maxFit)
        case 2 => ThrowPower(throw2.speedX, avgY, Configuration.maxFit)
        case 3 => ThrowPower(avgX, throw1.speedY, Configuration.maxFit)
        case 4 => ThrowPower(avgX, throw2.speedY, Configuration.maxFit)
        case 5 => ThrowPower(throw1.speedX, throw2.speedY, Configuration.maxFit)
        case 6 => ThrowPower(throw2.speedX, throw1.speedY, Configuration.maxFit)
      }
    }

    Random.shuffle(bestThrows)
    var crossedSpecies = new ArrayBuffer[ThrowPower]

    for (i <- 0 until (bestThrows.size - 1) by 2) {
      crossedSpecies += crossTwo(bestThrows(i), bestThrows(i+1))
    }

    crossedSpecies.toList
  }

  def generateNextGeneration(throws: List[ThrowPower]): List[ThrowPower] = {
    val chosenParents = getParents(throws)
    val mutatedParents = mutateGen(throws)
    val crossedParents = crossGen(throws)
    val newGen = generateFirstGen(Configuration.populationSize / 5)

    val nextGen: List[ThrowPower] = chosenParents ++ mutatedParents ++ crossedParents ++ newGen
    for (t <- nextGen) {
      t.landingDiff()
    }
    val nextGenSeriously = nextGen.toSet[ThrowPower].toList.sortBy(_.fitness).take(Configuration.populationSize)
    nextGenSeriously
  }


  def getParents(throws: List[ThrowPower]): List[ThrowPower] = {
    var highest = 0.0
    var lowest = 0.0
    for (particularThrow <- throws) {
      particularThrow.landingDiff()
      highest = highest.max(particularThrow.fitness)
      lowest = lowest.min(particularThrow.fitness)
    }

    for (particularThrow <- throws) {
      particularThrow.fitness = highest - particularThrow.fitness
    }

    val sortedThrows = throws.sortBy(_.fitness)

    for (t <- sortedThrows) {
      println(t.fitness)
    }

    var chosenParents = new ArrayBuffer[ThrowPower]

    val fitnessSum = sortedThrows.foldLeft(0.0) {
      (acc, i) => acc + i.fitness
    }

    for (i <- 0 until Configuration.populationSize) {
      addOneParent()
    }

    def addOneParent(): Unit = {
      val random = Random.nextDouble() * fitnessSum
      var sumToThisPoint = 0.0

      for (particularThrow <- sortedThrows){
        sumToThisPoint += particularThrow.fitness
        if (sumToThisPoint >= random){
          chosenParents += particularThrow
          return
        }
      }
    }

    chosenParents.toList

  }

}
