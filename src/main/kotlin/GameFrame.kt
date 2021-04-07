import javax.swing.JFrame

object GameFrame : JFrame("tenk") {
    init  {
        InputHandler
        defaultCloseOperation = EXIT_ON_CLOSE
        isLocationByPlatform = true
        isVisible = true
        contentPane.addMouseListener(InputHandler)
        addKeyListener(InputHandler)
        contentPane.addMouseMotionListener(InputHandler)
        contentPane.addMouseWheelListener(InputHandler)
        contentPane.add(GameScreen)
        pack()
    }
}