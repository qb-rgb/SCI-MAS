package wator

import java.io.{File, BufferedWriter, FileWriter}

object WatorData {

  private var tunasNb: List[Int] = Nil

  private var sharksNb: List[Int] = Nil

  def raz: Unit = {
    this.tunasNb = Nil
    this.sharksNb = Nil
  }

  /** Add a tunas number data.
    *
    * @param nb tunas number to add
    */
  def addTunasNb(nb: Int): Unit = this.tunasNb = this.tunasNb ++ List(nb)

  /** Add a sharks number data.
    *
    * @param nb sharks number to add
    */
  def addSharksNb(nb: Int): Unit = this.sharksNb = this.sharksNb ++ List(nb)

  def writeBalance: Unit = {
    def coupleToString(couple: (Int, Int)): String =
      couple._1 + "\t" + couple._2

    val file = new File("balance.data")
    val bw = new BufferedWriter(new FileWriter(file))
    val text = ((this.tunasNb zip this.sharksNb) map coupleToString) mkString "\n"

    bw.write(text)
    bw.close
  }

  def writePop: Unit = {
    def tripleToString(triple: (Int, Int, Int)): String =
      triple._1 + "\t" + triple._2 + "\t" + triple._3

    val file = new File("pop.data")
    val bw = new BufferedWriter(new FileWriter(file))
    val tripleList = ((1 to this.tunasNb.length) zip this.tunasNb) zip this.sharksNb map {
      case ((x, y), z) => (x, y, z)
    }
    val text = (tripleList map tripleToString) mkString "\n"

    bw.write(text)
    bw.close
  }

}
