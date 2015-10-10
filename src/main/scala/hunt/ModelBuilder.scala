package hunt

/** Build a hunt environment from a text file model.
  *
  * @author Quentin Baert
  */
object ModelBuilder {

  import scala.io.Source

  // Get the content of a file
  private def getContent(fileName: String): String = {
    val source = Source fromFile fileName
    val content = try source.mkString finally source.close

    content
  }

  // Get the lines of a file
  private def getLines(content: String): List[String] = {
    val lines = ((content split "\n") filterNot { _.trim.isEmpty }).toList

    assert(!lines.isEmpty)

    val firstLineLength = lines.head.length

    assert(lines forall { _.length == firstLineLength })

    lines
  }

  // Position each char in the lines
  private def positionLines(lines: List[String]): List[((Int, Int), Char)] = {
    def positionHorizontalLine(ordinate: Int, line: String): List[((Int, Int), Char)] =
      (line.zipWithIndex map { case (c, i) => ((i, ordinate), c) }).toList

    lines.zipWithIndex flatMap { case (l, i) => positionHorizontalLine(i, l) }
  }

  /** Build a hunt environment for a text file model.
    *
    * @param fileName name of the text file model
    * @return initialize hunt environment
    */
  def buildEnvFromModel(fileName: String): HuntEnvironment = {
    val content = this getContent fileName
    val lines = this getLines content
    val width = lines.head.length
    val height = lines.length
    val env = new HuntEnvironment(width, height)

    def processCharAt(posX: Int, posY: Int, c: Char): Unit = c match {
      case '#' => env.addAgent(new Wall(env, posX, posY))
      case 'P' => env.addAgent(new Prey(env, posX, posY))
      case 'H' => env.addAgent(new Hunter(env, posX, posY))
      case _   =>
    }

    val posLines = this positionLines lines

    posLines foreach {
      case ((x, y), c) => processCharAt(x, y, c)
    }

    env
  }

}
