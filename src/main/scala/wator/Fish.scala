package wator

import core.Agent

/** Fish evolving in a prey-predator simulation.
  *
  * @author Quentin Baert
  */
trait Fish extends Agent {

  /** Time before the fish can reproduce itself. */
  def breedingAge: Int

}
