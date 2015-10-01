package core

import javax.swing.JFrame

/** Frame to show the particle room evolve.
  *
  * @param width width of the particle room
  * @param height height of the particle room
  * @param nbParticles numbre of particles to put in the room
  * @param slow time to wait between two turn of the environment
  *
  * @author Quentin Baert
  */
abstract class MASView(val mas: MAS, val agentSize: Int, val slow: Int) extends JFrame {

  def updateContainer: Unit

  /** Begin the simulation of the particle room. */
  def run: Unit = {
    // Simule one turn
    this.mas.runOneTurn

    // Wait a little..
    try {
      Thread.sleep(this.slow)
    } catch {
      case e: Exception => {
        e.printStackTrace
        sys.exit(1)
      }
    }

    // And show the environment state
    this.updateContainer

    run
  }

}
