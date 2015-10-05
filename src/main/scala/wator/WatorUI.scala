package wator

import core.{Agent, MAS, MASView}
import java.awt.Color
import java.awt.Graphics
import javax.swing.{JPanel, JFrame}

// Canvas to print the environment
private class Canvas(width: Int, height: Int, mas: MAS, agentSize: Int) extends JPanel {

  private def attributeColor(a: Agent): Color = a match {
    case _: Tuna => Color.BLUE
    case _       => Color.RED
  }

  private def agentsAndColors =
    for (a <- this.mas.agents) yield (a, this attributeColor a)

  override def paintComponent(g: Graphics): Unit = {
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, this.width, this.height)

    for ((fish, color) <- this.agentsAndColors) {
      g.setColor(color)
      g.fillOval(
        fish.posX * this.agentSize,
        fish.posY * this.agentSize,
        this.agentSize,
        this.agentSize
      )
    }
  }

}

/** User interface of the Wator prey-predator simulation. */
class WatorUI(
  override val mas: MAS,
  override val agentSize: Int,
  override val slow: Int
) extends MASView(mas, agentSize, slow) {

  private val propWidth = this.mas.environment.width * this.agentSize
  private val propHeight = this.mas.environment.height * this.agentSize

  // Parameters of the frame
  this.setTitle("Wator")
  this.setSize(this.propWidth, this.propHeight)
  this.setLocationRelativeTo(null)
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  this.setContentPane(container)
  this.setVisible(true)

  override def getCanvas: JPanel =
    new Canvas(this.propWidth, this.propHeight, this.mas, this.agentSize)

}
