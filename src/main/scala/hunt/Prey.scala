package hunt

import core.{Agent, Environment}
import scala.util.Random

/** Represent a prey in a hunt simulation.
  *
  * @constructor create a new prey.
  * @param environment environment of the prey
  * @param posX abscissa position of the prey
  * @param posY ordinate position of the prey
  *
  * @author Quentin Baert
  */
class Prey(
  override val environment: HuntEnvironment,
  var posX: Int,
  var posY: Int
) extends Agent {

  // Determine if the prey is dead or not
  private var isDead = false

  /** Update the postion of the prey's position.
    *
    * @param x new abscissa of the prey
    * @param y new ordinate of the prey
    */
  protected def updatePos(x: Int, y: Int): Unit = {
    this.posX = x
    this.posY = y
  }

  /** Kill the prey. */
  def kill: Unit = this.isDead = true

  /** Get the neighbourhood of the prey.
    *
    * @return list which contains the neighbourhood of the prey
    */
  def getNeighbourhood: List[(Int, Int)] = { for {
    x <- (this.posX - 1) to (this.posX + 1)
    y <- (this.posY - 1) to (this.posY + 1)
    if this.environment.isUnder(x, y)
  } yield (x, y) }.toList

  /** Get a random free cell around the prey if it exists.
    *
    * @return an optional free cell around the prey
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

  /** Make the prey try to move in a free cell around it. */
  protected def moveInFreeCell: Unit = this.getFreeCell match {
    case Some((x, y)) => {
      this.environment.emptyCell(this.posX, this.posY)
      this.updatePos(x, y)
      this.environment.addAgent(this)
    }
    case None         =>
  }

  /** @see core.Agent.decide() */
  override def decide: Unit =
    if (!this.isDead) this.moveInFreeCell

}
