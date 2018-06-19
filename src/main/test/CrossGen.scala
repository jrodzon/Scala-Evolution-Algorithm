import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActor, TestActors, TestKit, TestProbe}
import evolution.ThrowPower
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class CrossGen() extends TestKit(ActorSystem("crossGenActor")) with BeforeAndAfterAll
  with Matchers with WordSpecLike with ImplicitSender {
  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  import evolution.CirclesProcesser._
  import actors.CrossActor

  "CrossGen actor" must {
    "return list with size of half the given population" in {
      val probe = TestProbe()
      val crossGen = system.actorOf(Props[CrossActor])
      val testSize = 50
      val generation = generateFirstGen(testSize)
      crossGen ! generation

      probe.expectMsg(List[ThrowPower])

    }
  }
}
