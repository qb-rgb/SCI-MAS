package particles

import scala.util.Random

/** Generate particules to put in an environment.
  *
  * @constructor create a new particles generator.
  * @param environment environment in which put the particles
  *
  * @author Quentin Baert
  */
class ParticlesGenerator(environment: ParticlesEnvironment) {

  // Range of the possible abscissa values
  private val abscissas = 0 until this.environment.width

  // Range of the possible ordinate values
  private val ordinates = 0 until this.environment.height

  // Range of directions values
  private val directions = -1 to 1

  // Generate one particle
  private def generateOne: Particle = {
    val posX = this.abscissas(Random.nextInt(this.environment.width))
    val posY = this.ordinates(Random.nextInt(this.environment.height))
    val dirX = this.directions(Random.nextInt(3))
    val dirY = this.directions(Random.nextInt(3))

    new Particle(this.environment, posX, posY, dirX, dirY)
  }

  /** Generate a given number of particules.
    *
    * @param n number of particles to generate
    * @return list of the particules
    */
  def generate(n: Int): List[Particle] = {
    def innerGenerate(nb: Int, particles: List[Particle]): List[Particle] =
      if (nb == 0)
        particles
      else {
        val p = this.generateOne

        if (particles exists (x => x.posX == p.posX && x.posY == p.posY))
          innerGenerate(nb, particles)
        else {
          this.environment.addAgent(p)
          innerGenerate(nb - 1, p :: particles)
        }
      }

    innerGenerate(n, Nil)
  }

}
