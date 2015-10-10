package wator

import core.Agent
import scala.util.Random

/** Fish evolving in a prey-predator simulation.
  *
  * @author Quentin Baert
  */
abstract class Fish(override val environment: WatorEnvironment) extends Agent {

  /** Time before the fish can reproduce itself. */
  protected var breedingAge: Int = _
  this.initBreedingAge

  /** Determine if the fish is dead or not. */
  protected var dead: Boolean = false

  /** Update the position of the fish.
    *
    * @param newX new abscissa of the fish
    * @param newY new ordinate of the fish
    */
  protected def updatePos(newX: Int, newY: Int): Unit

  /** Initialize the breedingAge of the fish. */
  protected def initBreedingAge: Unit

  /** Decrease the breeding age of the fish. */
  protected def decreaseBreedingAge: Unit

  /** Kill the fish. */
  def kill: Unit = this.dead = true

  /** Determine if the fish is dead or not. */
  def isDead: Boolean = this.dead

  /** Get the neighbourhood of the fish.
    *
    * @return list which contains the neighbourhood of the fish
    */
  protected def getNeighbourhood: List[(Int, Int)] = { for {
    x <- (this.posX - 1) to (this.posX + 1)
    y <- (this.posY - 1) to (this.posY + 1)
    if this.environment.isUnder(x, y)
  } yield (x, y) }.toList

  /** Get a random free cell around the fish if it exists.
    *
    * @return an optional free cell around the fish
    */
  protected def getFreeCell: Option[(Int, Int)] = {
    val nghds = this.getNeighbourhood

    val freeNghds = nghds filter {
      case (x, y) => this.environment.isEmpty(x, y)
    }

    if (freeNghds.isEmpty)
      None
    else
      Some((Random shuffle freeNghds).head)
  }

  /** Make the fish reproduce itself in the given cell.
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    */
  protected def reproduceIn(x: Int, y: Int): Unit

  /** Test if the fish can reproduce itself. If yes, apply the reproduction. */
  protected def testReproduction: Unit =
    if (this.breedingAge <= 0)
      this.getFreeCell match {
        case Some((x, y)) => {
          this.reproduceIn(x, y)
          this.initBreedingAge
        }
        case _            =>
      }
    else this.decreaseBreedingAge

  /** Make the fish try to move in a free cell around it. */
  protected def moveInFreeCell: Unit = this.getFreeCell match {
    case Some((x, y)) => {
      this.environment.emptyCell(this.posX, this.posY)
      this.updatePos(x, y)
      this.environment.addAgent(this)
    }
    case None         =>
  }

}
