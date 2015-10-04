package core

import scala.util.Random

/** Represent a Multi-agent system.
  * The MAS object give the speak at all the agents in its environment.
  *
  * @constructor create a new Multi-agent system.
  * @param environment environment of the MAS
  *
  * @author Quentin Baert
  */
class MAS(val environment: Environment) {

  /** Agents in the environment. */
  def agents: List[Agent] = this.environment.getAgents

  /** Run one turn of the MAS.
    * In one turn, each agent of the environment speak one time.
    */
  def runOneTurn: Unit = {
    val orderedAgents = Random shuffle this.agents

    orderedAgents foreach (_.decide)
  }

}
