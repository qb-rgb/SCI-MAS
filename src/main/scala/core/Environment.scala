package core

/** Environment of a SMA.
  *
  * @constructor create a new environment of given width and height.
  * @param width width of the environment
  * @param height height of the environment
  *
  * @author Quentin Baert
  */
abstract class Environment(width: Int, height: Int) {

  /** Cells of the environment. */
  val cells = Array.ofDim[Cell](width, height)

  /** Determine if a cell of the environment is empty or not.
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    * @return true if the cell is empty, false otherwise
    */
  def isEmpty(x: Int, y: Int): Boolean = this.cells(x)(y) == None

  /** Determine if a cell is under the environment or not.
    *
    * @param x abscissa of the cell
    * @param y ordinate of the cell
    * @return true if the cell is under the environment, false otherwise
    */
  def isUnder(x: Int, y: Int): Boolean =
    (0 <= x && x < this.width) && (0 <= y && y < this.height)

}
