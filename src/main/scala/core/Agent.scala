package core

/** Agent of a MSA.
  *
  * @author Quentin Baert
  */
trait Agent {

  /** Environment in which the agent evolve. */
  val environment: Environment

  /** Abscissa of the agent in its environment. */
  def posX: Int

  /** Ordinate of the agent in its environment. */
  def posY: Int

  /** Make the agent take a decision. */
  def decide: Unit

}
