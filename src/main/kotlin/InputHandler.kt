import java.awt.event.*

object InputHandler : KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    override fun keyTyped(e: KeyEvent) {

    }

    override fun keyPressed(e: KeyEvent) {
        GameScreen.onKeyDownEvent(e.keyCode)
    }

    override fun keyReleased(e: KeyEvent) {
        GameScreen.onKeyUpEvent(e.keyCode)
    }

    override fun mouseClicked(e: MouseEvent) {

    }

    override fun mousePressed(e: MouseEvent) {
        GameScreen.onMouseDownEvent(e.point, e.button)
    }

    override fun mouseReleased(e: MouseEvent) {
        GameScreen.onMouseUpEvent(e.point, e.button)

    }

    override fun mouseEntered(e: MouseEvent) {

    }

    override fun mouseExited(e: MouseEvent) {

    }

    override fun mouseDragged(e: MouseEvent) {
       GameScreen.onMouseDraggedEvent(e.point)
    }

    override fun mouseMoved(e: MouseEvent) {

    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        GameScreen.onMouseWheelScroll(e.point, e.wheelRotation)
    }
}