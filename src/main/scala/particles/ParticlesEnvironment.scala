package particles

import core.Environment

class ParticlesEnvironment(
  override val width: Int,
  override val height: Int
) extends Environment(width, height) {

  // Particles and their positions
  private var particles: Map[(Int, Int), Particle] = Map()

  /** @see core.Environment.getAgentAt() */
  override def getAgentAt(x: Int, y: Int): Particle =
    this.particles(x, y)

  /** @see core.Environment.emptyCell() */
  override def emptyCell(x: Int, y: Int): Unit = {
    this.particles = this.particles - ((x, y))
    super.emptyCell(x, y)
  }

  def addAgent(a: Particle): Unit = {
    this.particles = this.particles + ((a.posX, a.posY) -> a)
    super.addAgent(a)
  }

}
