package hunt

import core.{Agent, MAS, MASView}
import java.awt.Color
import java.awt.Graphics
import javax.swing.{JPanel, JFrame, WindowConstants}

// Canvas to print the environment
private class Canvas(width: Int, height: Int, mas: MAS, agentSize: Int) extends JPanel {

  private def attributeColor(a: Agent): Color = a match {
    case _: Wall   => Color.GRAY
    case _: Prey   => Color.GREEN
    case _: Hunter => Color.RED
  }

  private def agentsAndColors =
    for (a <- this.mas.agents) yield (a, this attributeColor a)

  override def paintComponent(g: Graphics): Unit = {
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, this.width, this.height)

    for ((agent, color) <- this.agentsAndColors) {
      g.setColor(color)
      g.fillOval(
        agent.posX * this.agentSize,
        agent.posY * this.agentSize,
        this.agentSize,
        this.agentSize
      )
    }
  }

}

/** User interface of the hunt simulation. */
class HuntUI(
  override val mas: HuntMAS,
  override val agentSize: Int,
  override val slow: Int
) extends MASView(mas, agentSize, slow) {

  private val propWidth = this.mas.environment.width * this.agentSize
  private val propHeight = this.mas.environment.height * this.agentSize

  // Parameters of the frame
  this.setTitle("Hunt")
  this.setSize(this.propWidth, this.propHeight)
  this.setLocationRelativeTo(null)
  this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  this.setContentPane(container)
  this.setVisible(true)

  override def stopCondition: Boolean =
    !this.mas.environment.containsPrey

  override def getCanvas: JPanel =
    new Canvas(this.propWidth, this.propHeight, this.mas, this.agentSize)

}
