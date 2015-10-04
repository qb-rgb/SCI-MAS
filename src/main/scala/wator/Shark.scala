package wator

import scala.util.Random

/** Represent a shark (the predator) in a prey-predator simulation.
  *
  * @constructor create a new shark in the environment.
  * @param environment environment of the shark
  * @param posX abscissa of the shark in its environment
  * @param posY oridnate of the shark in its environment
  *
  * @author Quentin Baert
  */
class Shark(
  override val environment: WatorEnvironment,
  var posX: Int,
  var posY: Int
) extends Fish(environment) {

  protected var starvationTime: Int = WatorConfig.sharkStarvationTime

  /** @see wator.Fish.updatePos() */
  protected def updatePos(newX: Int, newY: Int): Unit = {
    this.posX = newX
    this.posY = newY
  }

  /** @see wator.Fish.initBreedingAge() */
  override protected def initBreedingAge: Unit =
    this.breedingAge = WatorConfig.sharkBreedingAge

  /** @see wator.Fish.decreaseBreedingAge() */
  override protected def decreaseBreedingAge: Unit =
    this.breedingAge -= 1

  /** @see wator.Fish.reproduceIn() */
  override protected def reproduceIn(x: Int, y: Int): Unit =
    this.environment.addAgent(new Shark(this.environment, x, y))

  /** Make the shark eat. Reset its starvation time. */
  protected def eat: Unit =
    this.starvationTime = WatorConfig.sharkStarvationTime

  /** Determine if the shark can eat a fish or not.
    *
    * @param f fish to eat by the shark
    * @return true if the shark can eat the fish, false otherwise
    */
  protected def canEat(f: Fish): Boolean = f match {
    case _: Tuna => true
    case _       => false
  }

  /** Smell if a tuna is around
    *
    * @return the position of a tuna if one is around
    */
  protected def smellTuna: Option[(Int, Int)] = {
    val nghd = this.getNeighbourhood

    val tunasPos = nghd filter {
      case (x, y) => this canEat (this.environment.getAgentAt(x, y))
    }

    if (tunasPos.isEmpty)
      None
    else
      Some((Random shuffle tunasPos).head)
  }

  /** Test if the shark can eat. If yes, eat. */
  protected def testEat: Boolean = this.smellTuna match {
    case Some((x, y)) => {
      this.environment.emptyCell(x, y)
      this.eat
      this.updatePos(x, y)
      this.environment.addAgent(this)
      true
    }
    case None         => false
  }

  /** @see core.Agent.decide() */
  override def decide: Unit =
    if (this.starvationTime == 0)
      this.environment.emptyCell(this.posX, this.posY)
    else {
      // Does the shark can eat ?
      if (!this.testEat)
        // If not, it try to move in a free cell
        this.moveInFreeCell

      // Does the shark reproduce itself ?
      this.testReproduction
    }

}
