package object hunt {

  import core.Agent

  def isPrey(a: Agent): Boolean = a match {
    case _: Prey => true
    case _       => false
  }

}
