package object wator {

  import core.Agent

  /** Determine if a an agent is a tuna or not. */
  def isTuna(a: Agent): Boolean = a match {
    case _: Tuna => true
    case _       => false
  }

}
