package hunt

/** Main program.
  *
  * @author Quentin Baert
  */
object HuntSimulation {

  type OptionMap = Map[String, String]

  // Error log
  private val errorLog = "ERROR"

  // Print the usage of the program
  private def printUsage: Unit = {
    println(
      "java -jar hunt.jar -model [m] -size [s] -sleep [z]"
    )
    println("With:")
    println("\t[m] path to the model text file")
    println("\t[s] size of the agent in the hunt environment")
    println("\t[z] time to wait between two turns of the simulation (in ms)")
    sys.exit(1)
  }

  // Catch the option of the program
  private def catchOptions(args: Array[String]): OptionMap = {
    def nextOption(map: OptionMap, args: List[String]): OptionMap = args match {
      // model option
      case "-model" :: value :: tail => nextOption(map ++ Map("-model" -> value), tail)

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
      val size = options("-size").toInt
      val sleep = options("-sleep").toInt
      val model = options("-model")

      val env = ModelBuilder.buildEnvFromModel(model)
      val mas = new HuntMAS(env)

      new HuntUI(mas, size, sleep).run
    } else
      this.printUsage
  }

}
