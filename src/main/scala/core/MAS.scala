package core

import scala.util.Random

abstract class MAS(val environment: Environment, val agents: List[Agent]) {

  def runOneTurn: Unit = {
    val orderedAgents = Random shuffle this.agents

    orderedAgents foreach (_.decide)
  }

}
