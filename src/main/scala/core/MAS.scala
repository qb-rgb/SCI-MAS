package core

import scala.util.Random

class MAS(val environment: Environment) {

  def agents: List[Agent] = this.environment.getAgents

  def runOneTurn: Unit = {
    val orderedAgents = Random shuffle this.agents

    orderedAgents foreach (_.decide)
  }

}
