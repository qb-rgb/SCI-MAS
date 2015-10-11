package particles

import core.MAS

/** Main program.
  *
  * @author Quentin Baert
  */
object ParticlesRoom {

  type OptionMap = Map[String, String]

  // Error log
  private val errorLog = "ERROR"

  // Print the usage of the program
  private def printUsage: Unit = {
    println(
      "java -jar particleRoom.jar -width [x] -height [y] " +
      "-number [n] -size [s] -sleep [z]"
    )
    println("With:")
    println("\t[x] width of the particle room")
    println("\t[y] height of the particle room")
    println("\t[n] number of particle to put in the particles room")
    println("\t[s] size of the particles")
    println("\t[z] time to wait between two turns of the simulation (in ms)")
    sys.exit(1)
  }

  // Catch the option of the program
  private def catchOptions(args: Array[String]): OptionMap = {
    def nextOption(map: OptionMap, args: List[String]): OptionMap = args match {
      // -width option
      case "-width" :: value :: tail => nextOption(map ++ Map("-width" -> value), tail)

      // -height option
      case "-height" :: value :: tail => nextOption(map ++ Map("-height" -> value), tail)

      // -number option
      case "-number" :: value :: tail => nextOption(map ++ Map("-number" -> value), tail)

      // -size option
      case "-size" :: value :: tail => nextOption(map ++ Map("-size" -> value), tail)

      // -sleep option
      case "-sleep" :: value :: tail => nextOption(map ++ Map("-sleep" -> value), tail)

      // Base case
      case Nil => map

      // Error case
      case _ => Map("unknownOption" -> this.errorLog)
    }

    val argsList = args.toList

    nextOption(Map(), argsList)
  }

  // Check if the options are ok
  private def checkOptions(map: OptionMap): Boolean =
    !map.isEmpty && !(map.values.toList contains this.errorLog)

  // Main
  def main(args: Array[String]): Unit = {
    val options = this.catchOptions(args)

    if (this.checkOptions(options)) {
      val width = options("-width").toInt
      val height = options("-height").toInt
      val number = options("-number").toInt
      val size = options("-size").toInt
      val sleep = options("-sleep").toInt

      val env = new ParticlesEnvironment(width, height)
      val particles = new ParticlesGenerator(env).generate(number)
      val mas = new MAS(env)

      new ParticlesRoomUI(mas, size, sleep).run
    } else
      this.printUsage
  }

}
