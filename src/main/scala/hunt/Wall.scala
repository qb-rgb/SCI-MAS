package hunt

import core.{Agent, Environment}

/** Represent a wall for a hunt simulation.
  *
  * @constructor create a new wall.
  * @param environment environment in which put the wall
  * @param posX abscissa position of the wall
  * @param posY ordinate position of the wall
  *
  * @author Quentin Baert
  */
class Wall(
  override val environment: HuntEnvironment,
  val posX: Int,
  val posY: Int
) extends Agent {

  /** @see core.Agent.decide() */
  override def decide: Unit = {}

}
