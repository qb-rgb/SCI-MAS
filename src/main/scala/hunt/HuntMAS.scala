package hunt

import core.{Agent, MAS}

/** Represent a Multi-agent system.
  * The MAS object give the speak at all the agents in its environment.
  *
  * @constructor create a new Multi-agent system.
  * @param environment environment of the MAS
  *
  * @author Quentin Baert
  */
class HuntMAS(override val environment: HuntEnvironment) extends MAS(environment)
