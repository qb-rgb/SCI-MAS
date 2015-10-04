package wator

class Tuna(
  override val environment: WatorEnvironment,
  var posX: Int,
  var posY: Int
) extends Fish(environment) {

  /** Breeding age of the tuna. */
  var breedingAge: Int = _
  this.initBreedingAge

  // Update positions of the fish
  protected def updatePos(newX: Int, newY: Int): Unit = {
    this.posX = newX
    this.posY = newY
  }

  /** @see wator.Fish.initBreedingAge() */
  override protected def initBreedingAge: Unit =
    this.breedingAge = WatorConfig.tunaBreedingAge

  override protected def decreaseBreedingAge: Unit =
    this.breedingAge -= 1

  /** @see wator.Fish.reproduceIn() */
  override def reproduceIn(x: Int, y: Int): Unit =
    this.environment.addAgent(new Tuna(this.environment, x, y))

  /** @see core.Agent.decide() */
  override def decide: Unit = {
    // Does the tuna reproduce itself ?
    this.testReproduction

    // Whatever happened, the tuna try to moveInFreeCell
    this.moveInFreeCell
  }

}
