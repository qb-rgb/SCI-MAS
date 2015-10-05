package wator

import scala.util.Random

/** Generate fishes to put in an environment.
  *
  * @constructor create a new fishes generator.
  * @param environment environment in which put the fishes
  *
  * @author Quentin Baert
  */
class FishesGenerator(
  val environment: WatorEnvironment,
  val config: WatorConfig
) {

  // Range of the possible abscissa values
  private val abscissas = 0 until this.environment.width

  // Range of the possible ordinate values
  private val ordinates = 0 until this.environment.height

  // Range of directions values
  private val directions = -1 to 1

  // Generate one fish
  private def generateOneFish(f: String): Fish = {
    val posX = this.abscissas(Random.nextInt(this.environment.width))
    val posY = this.ordinates(Random.nextInt(this.environment.height))

    f match {
      case "tuna" =>
        new Tuna(this.environment, posX, posY, this.config.tunaBreedingAge)
      case _      =>
        new Shark(
          this.environment,
          posX,
          posY,
          this.config.sharkBreedingAge,
          this.config.sharkStarvationTime
        )
    }
  }

  /** Generate a given number of fishes.
    *
    * @param nbTuna number of tuna to generate
    * @param nbShark number of shark to generate
    * @return list of the generated fishes
    */
  def generate(nbTuna: Int, nbShark: Int): List[Fish] = {
    def innerGenerate(nb: Int, f: String, fishes: List[Fish]): List[Fish] =
      if (nb == 0)
        fishes
      else {
        val fish = this.generateOneFish(f)

        if (this.environment.isFull(fish.posX, fish.posY))
          innerGenerate(nb, f, fishes)
        else {
          this.environment.addAgent(fish)
          innerGenerate(nb - 1, f, fish :: fishes)
        }
      }

    innerGenerate(nbShark, "shark", innerGenerate(nbTuna, "tuna", Nil))
  }

}
