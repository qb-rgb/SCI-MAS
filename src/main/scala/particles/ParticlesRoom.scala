package particles

import core.{MAS, MASView}
import scala.util.Random
import java.awt.Color
import java.awt.Graphics
import javax.swing.{JPanel, JFrame}

// Canvas to print the environment
private class Canvas(width: Int, height: Int, mas: MAS, agentSize: Int) extends JPanel {

  private val colors = List(
    Color.BLUE,
    Color.RED,
    Color.CYAN,
    Color.GREEN,
    Color.MAGENTA,
    Color.ORANGE,
    Color.PINK
  )

  private def pickRandomColor: Color = colors(Random nextInt colors.length)

  private val agentsAndColors =
    for (a <- this.mas.agents) yield (a, pickRandomColor)

  override def paintComponent(g: Graphics): Unit = {
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, this.width, this.height)

    for ((particle, color) <- this.agentsAndColors) {
      g.setColor(color)
      g.fillOval(
        particle.posX * this.agentSize,
        particle.posY * this.agentSize,
        this.agentSize,
        this.agentSize
      )
    }
  }

}

class ParticlesRoomUI(
  override val mas: MAS,
  override val agentSize: Int,
  override val slow: Int
) extends MASView(mas, agentSize, slow) {

  private val propWidth = this.mas.environment.width * this.agentSize
  private val propHeight = this.mas.environment.height * this.agentSize

  // Parameters of the frame
  this.setTitle("Particles Room")
  this.setSize(this.propWidth, this.propHeight)
  this.setLocationRelativeTo(null)
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  private val container = new Canvas(this.propWidth, this.propHeight, this.mas, this.agentSize)

  this.setContentPane(container)
  this.setVisible(true)

  override def updateContainer: Unit =
    this.container.repaint()

}
