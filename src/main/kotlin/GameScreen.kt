import fuzzyMath.components.Vector2
import fuzzyMath.components.*
import fuzzyMath.*
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Point
import java.awt.color.ColorSpace
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB
import java.awt.image.ColorModel
import javax.swing.JPanel
import kotlin.math.pow
import kotlin.random.Random
import fuzzyMath.Fractalizer
import kotlin.concurrent.thread
import kotlin.math.log

object GameScreen : JPanel() {
    val dimensions = Point(1280 / 2, 720 / 2)
    val image = BufferedImage(dimensions.x, dimensions.y, TYPE_INT_RGB)

    var referenceScale = 350.0
    var referenceTransform = Vector2.zero()//Vector2.fromPoint(dimensions) / (2 * referenceScale)
    private var draggedFrom = Point(0, 0)

    private val keysDown = mutableSetOf<Int>()
    private val mouseButtonsDown = mutableSetOf<Int>()

    init {

    }

    val numberOfThreads = 16

    override fun paint(g: Graphics) {
        g.drawImage(image, 0, 0, null)
    }

    fun drawFractal(){
        for (threadNum in 0 until numberOfThreads) {
            thread(start = true, name= "test") {
                for (x in ((dimensions.x / numberOfThreads) * threadNum) until ((dimensions.x / numberOfThreads) * (threadNum + 1))) {
                    for (y in 0 until dimensions.y) {
                        val c =
                            Vector2(
                                (x / referenceScale - referenceTransform.x),
                                (y / referenceScale - referenceTransform.y)
                            )
                        val value = (log(Fractalizer.collatz(c).toDouble(), 1.03) % 256)
                        val R = (value * 3).toInt() shl 16
                        val G = (value * 4).toInt() shl 8
                        val B = (value * 5).toInt() shl 0
                        image.setRGB(x, y, (R + G + B))
                    }
                }
            }
        }
    }

    override fun getPreferredSize(): Dimension {
        return Dimension(dimensions.x, dimensions.y)
    }

    fun onKeyDownEvent(keyCode: Int) {
        keysDown.add(keyCode)
    }

    fun onKeyUpEvent(keyCode: Int) {
        keysDown.remove(keyCode)
    }

    fun onMouseDownEvent(point: Point, buttonId: Int) {
        mouseButtonsDown.add(buttonId)
        if (buttonId == 3) {
            draggedFrom = point
        }
    }

    fun onMouseUpEvent(point: Point, buttonId: Int) {
        mouseButtonsDown.remove(buttonId)
    }

    fun onMouseDraggedEvent(point: Point) {
        if (mouseButtonsDown.contains(3)) {
            referenceTransform += (Vector2.fromPoint(point) - Vector2.fromPoint(draggedFrom)) / referenceScale
            draggedFrom = point
            drawFractal()
        }
    }

    fun onMouseWheelScroll(point: Point, amount: Int) {
        val scaleFactor = 0.1
        val scalar =
            if (amount > 0) (1 - scaleFactor).pow(amount.toDouble()) else (1 + scaleFactor).pow(-amount.toDouble())
        referenceScale *= scalar
        drawFractal()
    }
}