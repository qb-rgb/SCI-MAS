package wator

import core.{Agent, MAS}
import scala.util.Random

/** Represent a Multi-agent system.
  * The MAS object give the speak at all the agents in its environment.
  *
  * @constructor create a new Multi-agent system.
  * @param environment environment of the MAS
  *
  * @author Quentin Baert
  */
class WatorMAS(override val environment: WatorEnvironment) extends MAS(environment) {

  private val data = WatorData
  data.raz

  // (Tuna, Shark)
  private def countPop: (Int, Int) = {
    def innerCount(fishes: List[Agent], nt: Int, ns: Int): (Int, Int) =
      if (fishes.isEmpty)
        (nt, ns)
      else fishes.head match {
        case _: Tuna  => innerCount(fishes.tail, nt + 1, ns)
        case _: Shark => innerCount(fishes.tail, nt, ns + 1)
      }

    innerCount(this.agents, 0, 0)
  }

  /** Run one turn of the MAS.
    * In one turn, each agent of the environment speak one time.
    */
  override def runOneTurn: Unit = {
    val orderedAgents = Random shuffle this.agents

    orderedAgents foreach { a =>
      if (!this.environment.isAffectedCell(a.posX, a.posY))
        a.decide
    }

    this.environment.initAffectedCells

    val (nt, ns) = this.countPop
    data.addTunasNb(nt)
    data.addSharksNb(ns)
  }

}
