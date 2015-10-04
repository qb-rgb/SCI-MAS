package wator

/** Represent a tuna (the prey) in a prey-predator simulation.
  *
  * @constructor create a new tuna in the environment.
  * @param environment environment of the tuna
  * @param posX abscissa of the tuna in its environment
  * @param posY ordinate of the tuna in its environment
  *
  * @author Quentin Baert
  */
class Tuna(
  override val environment: WatorEnvironment,
  var posX: Int,
  var posY: Int
) extends Fish(environment) {

  /** @see wator.Fish.updatePos() */
  protected def updatePos(newX: Int, newY: Int): Unit = {
    this.posX = newX
    this.posY = newY
  }

  /** @see wator.Fish.initBreedingAge() */
  override protected def initBreedingAge: Unit =
    this.breedingAge = WatorConfig.tunaBreedingAge

  /** @see wator.Fish.decreaseBreedingAge() */
  override protected def decreaseBreedingAge: Unit =
    this.breedingAge -= 1

  /** @see wator.Fish.reproduceIn() */
  override protected def reproduceIn(x: Int, y: Int): Unit =
    this.environment.addAgent(new Tuna(this.environment, x, y))

  /** @see core.Agent.decide() */
  override def decide: Unit = {
    // The tuna try to move in free cell
    this.moveInFreeCell

    // Does the tuna reproduce itself ?
    this.testReproduction
  }

}
