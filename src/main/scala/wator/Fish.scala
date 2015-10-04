package wator

import core.Actor

/** Fish evolving in a prey-predator simulation.
  *
  * @author Quentin Baert
  */
trait Fish extends Actor {

  /** Time before the fish can reproduce itself. */
  def breedingAge: Int

}
