package wator

import core.Environment

/** Environment for a prey-predator simulation.
  *
  * @constructor create a new environment for a prey-predator simulation.
  * @param width width of the environment
  * @param height height of the environment
  *
  * @author Quentin Baert
  */
class WatorEnvironment(
  override val width: Int,
  override val height: Int
) extends Environment(width, height) {

  // Fishes and their positions
  private var fishes: Map[(Int, Int), Fish] = Map()

  // Number of sharks in the environment
  private var sharksNb: Int = 0

  // Number of tunas in the environment
  private var tunasNb: Int = 0

  /** @see core.Environment.getAgentAt() */
  override def getAgentAt(x: Int, y: Int): Fish = this.fishes(x, y)

  /** @see core.Environment.getAgents() */
  override def getAgents: List[Fish] = this.fishes.values.toList

  /** Get the number of tunas in the environment. */
  def getTunasNb: Int = this.tunasNb

  /** Get the number of sharks in the environment. */
  def getSharksNb: Int = this.sharksNb

  /** @see core.Environment.emptyCell() */
  override def emptyCell(x: Int, y: Int): Unit = {
    if (isTuna(this.getAgentAt(x, y)))
      this.tunasNb -= 1
    else
      this.sharksNb -= 1

    this.fishes = this.fishes - ((x, y))
    super.emptyCell(x, y)
  }

  /** @see core.Environment.addAgent() */
  def addAgent(f: Fish): Unit = {
    this.fishes = this.fishes + ((f.posX, f.posY) -> f)

    if (isTuna(f))
      this.tunasNb += 1
    else
      this.sharksNb += 1

    super.addAgent(f)
  }

}
