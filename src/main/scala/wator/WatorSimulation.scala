package wator

import core.MAS

/** Main program.
  *
  * @author Quentin Baert
  */
object WatorSimulation {

  type OptionMap = Map[String, String]

  // Error log
  private val errorLog = "ERROR"

  // Print the usage of the program
  private def printUsage: Unit = {
    println("TO DO")
    sys.exit(1)
  }

  // Catch the option of the program
  private def catchOptions(args: Array[String]): OptionMap = {
    def nextOption(map: OptionMap, args: List[String]): OptionMap = args match {
      // -width option
      case "-width" :: value :: tail => nextOption(map ++ Map("-width" -> value), tail)

      // -height option
      case "-height" :: value :: tail => nextOption(map ++ Map("-height" -> value), tail)

      // -nTuna option
      case "-nTuna" :: value :: tail => nextOption(map ++ Map("-nTuna" -> value), tail)

      // -nShark option
      case "-nShark" :: value :: tail => nextOption(map ++ Map("-nShark" -> value), tail)

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
      val nTuna = options("-nTuna").toInt
      val nShark = options("-nShark").toInt
      val size = options("-size").toInt
      val sleep = options("-sleep").toInt

      val env = new WatorEnvironment(width, height)
      val fishes = new FishesGenerator(env).generate(nTuna, nShark)
      val mas = new MAS(env)

      new WatorUI(mas, size, sleep).run
    } else
      this.printUsage
  }

}
