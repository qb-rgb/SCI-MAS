package hunt

import core.{Agent, Environment}

class HuntEnvironment(
  override val width: Int,
  override val height: Int
) extends Environment(width, height) {

  private var agents: Map[(Int, Int), Agent] = Map()

  /** @see core.Environment.agents() */
  override def getAgents: List[Agent] = this.agents.values.toList

  /** @see core.Environment.getAgentAt() */
  override def getAgentAt(x: Int, y: Int): Agent =
    this.agents(x, y)

  /** @see core.Environment.emptyCell() */
  override def emptyCell(x: Int, y: Int): Unit = {
    this.agents = this.agents - ((x, y))
    super.emptyCell(x, y)
  }

  /** @see core.Environment.addAgent() */
  override def addAgent(a: Agent): Unit = {
    this.agents = this.agents + ((a.posX, a.posY) -> a)
    super.addAgent(a)
  }

  private def getDijkstraFor(p: Prey): Array[Array[Int]] = {
    def buildDijkstra(array: Array[Array[Int]], pos: List[(Int, Int)], degree: Int): Unit =
      for {
        (x, y) <- pos
        if (this.isUnder(x, y))
      } {
        array(x)(y) = degree

        val npos: List[(Int, Int)] = (for {
          nx <- (x - 1) to (x + 1)
          ny <- (y - 1) to (y + 1)
          if this.isUnder(x, y) && this.isEmpty(x, y)
        } yield (nx, ny)).toList

        buildDijkstra(array, npos, degree + 1)
      }

    val array = Array.ofDim[Int](this.width, this.height)

    buildDijkstra(array, p.getNeighbourhood, 1)

    array
  }

  def getDijkstraForAllPreys: List[Array[Array[Int]]] = (for {
    a <- this.getAgents
    if (isPrey(a))
  } yield (this getDijkstraFor a.asInstanceOf[Prey])).toList

}
