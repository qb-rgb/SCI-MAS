package core

/** Agent of a MSA.
  *
  * @author Quentin Baert
  */
trait Agent {

  val environment: Environment

  def posX: Int

  def posY: Int

  def decide: Unit

}
