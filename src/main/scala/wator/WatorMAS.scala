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

    data.addTunasNb(this.environment.getTunasNb)
    data.addSharksNb(this.environment.getSharksNb)
  }

}
