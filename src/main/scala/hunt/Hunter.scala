package hunt

import core.{Agent}
import scala.util.Random

class Hunter(
  override val environment: HuntEnvironment,
  var posX: Int,
  var posY: Int
) extends Agent {

  /** Update the postion of the hunter's position.
    *
    * @param x new abscissa of the prey
    * @param y new ordinate of the prey
    */
  protected def updatePos(x: Int, y: Int): Unit = {
    this.posX = x
    this.posY = y
  }

  /** Get the neighbourhood of the hunter.
    *
    * @return list which contains the neighbourhood of the prey
    */
  protected def getNeighbourhood: List[(Int, Int)] = { for {
    x <- (this.posX - 1) to (this.posX + 1)
    y <- (this.posY - 1) to (this.posY + 1)
    if this.environment.isUnder(x, y)
  } yield (x, y) }.toList

  // Find the min value in a array of (value, position)
  private def findMin(
    l: List[(Int, (Int, Int))],
    actualMin: Int,
    actualMinPos: (Int, Int)
  ): (Int, (Int, Int)) =
    if (l.isEmpty)
      (actualMin, actualMinPos)
    else {
      val (value, pos) = l.head

      if (value < actualMin)
        findMin(l.tail, value, pos)
      else
        findMin(l.tail, actualMin, actualMinPos)
    }

  // Find the next position of the hunter regarding preys position
  private def getMinInArray(array: Array[Array[Int]]): (Int, (Int, Int)) = {
    val valueAndPos = for {
      (x, y) <- this.getNeighbourhood
      if (this.environment.isEmpty(x, y))
    } yield (array(x)(y), (x, y))

    val (firstValue, firstPos) = valueAndPos.head

    this.findMin(valueAndPos.tail, firstValue, firstPos)
  }

  /** Smell if a prey is around
    *
    * @return the position of a prey if one is around
    */
  protected def smellPrey: Option[(Int, Int)] = {
    val nghd = this.getNeighbourhood

    val preyPos = nghd filter {
      case (x, y) =>
        this.environment.isFull(x, y) && isPrey(this.environment.getAgentAt(x, y))
    }

    if (preyPos.isEmpty)
      None
    else
      Some((Random shuffle preyPos).head)
  }

  /** @see core.Agent.decide() */
  override def decide: Unit = this.smellPrey match {
    case Some((x, y)) => {
      this.environment.emptyCell(x, y)
      this.environment.getAgentAt(x, y).asInstanceOf[Prey].kill
      this.environment.emptyCell(this.posX, this.posY)
      this.updatePos(x, y)
      this.environment.addAgent(this)
    }

    case None         => {
      val dijkstras = this.environment.getDijkstraForAllPreys
      val mins = dijkstras map { this getMinInArray _ }
      val (_, (x, y)) = this.findMin(mins.tail, mins.head._1, mins.head._2)

      this.environment.emptyCell(this.posX, this.posY)
      this.updatePos(x, y)
      this.environment.addAgent(this)
    }
  }

}
