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

  // Determine if a cell was affected during a turn
  private val affectedCells = Array.ofDim[Boolean](this.width, this.height)
  this.initAffectedCells

  /** Initialize the affected cells of the environment. */
  def initAffectedCells: Unit = for {
    x <- 0 until this.width
    y <- 0 until this.height
  } this.affectedCells(x)(y) = false

  /** Affect the cell in the given position.
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    */
  def affectCell(x: Int, y: Int): Unit =
    this.affectedCells(x)(y) = true

  /** Determine if a cell was affected during a turn of the simulation. */
  def isAffectedCell(x: Int, y: Int): Boolean = this.affectedCells(x)(y)

  /** @see core.Environment.getAgentAt() */
  override def getAgentAt(x: Int, y: Int): Fish = this.fishes(x, y)

  /** @see core.Environment.getAgents() */
  override def getAgents: List[Fish] = this.fishes.values.toList

  /** @see core.Environment.emptyCell() */
  override def emptyCell(x: Int, y: Int): Unit = {
    this.fishes = this.fishes - ((x, y))
    super.emptyCell(x, y)
  }

  /** @see core.Environment.addAgent() */
  def addAgent(f: Fish): Unit = {
    this.fishes = this.fishes + ((f.posX, f.posY) -> f)
    super.addAgent(f)
  }

}
