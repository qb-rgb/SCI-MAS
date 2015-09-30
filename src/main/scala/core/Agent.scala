package core

/** Agent of a MSA.
  *
  * @author Quentin Baert
  */
abstract class Agent(environment: Environment) {

  def decide: Unit

}
