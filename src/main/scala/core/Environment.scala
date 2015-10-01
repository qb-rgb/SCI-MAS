package core

/** Environment of a SMA.
  *
  * @constructor create a new environment of given width and height.
  * @param width width of the environment
  * @param height height of the environment
  *
  * @author Quentin Baert
  */
abstract class Environment(val width: Int, val height: Int) {


  protected val cells = Array.ofDim[Cell](this.width, this.height)

  /** Determine if a cell of the environment is empty or not.
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    * @return true if the cell is empty, false otherwise
    */
  def isEmpty(x: Int, y: Int): Boolean = this.cells(x)(y) == None

  /** Determine if a cell of the environment is full or not
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    * @return true if the cell is full, false otherwise
    */
  def isFull(x: Int, y: Int): Boolean = !this.isEmpty(x, y)

  /** Determine if a cell is under the environment or not.
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    * @return true if the cell is under the environment, false otherwise
    */
  def isUnder(x: Int, y: Int): Boolean =
    (0 <= x && x < this.width) && (0 <= y && y < this.height)

  /** Determine if a cell is out of the environment or not.
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    * @return true if the cell is out of the environment, false otherwise
    */
  def isOut(x: Int, y: Int): Boolean = !this.isUnder(x, y)

  /** Add an agent in the environment.
    *
    * @param a agent to add in the environment
    */
  def addAgent(a: Agent): Unit =
    this.cells(a.posX)(a.posY) = Some(a)

  /** Empty a cell.
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    */
  def emptyCell(x: Int, y: Int): Unit =
    this.cells(x)(y) = None

  def getAgentAt(x: Int, y: Int): Agent =
    this.cells(x)(y).get

}
