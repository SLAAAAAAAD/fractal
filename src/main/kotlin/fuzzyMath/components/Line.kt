package fuzzyMath.components

import fuzzyMath.*

class Line(val p1: Vector2, val p2: Vector2): Shape {

    val p2Relative get() = p2 - p1

    val xIntercept
        get() = if (p1.y * p2.y > 0) null else lerp(p1.x, p2.x, alerp(p1.y, p2.y, 0.0))

}

operator fun Line.plus(p: Vector2) = Line(p1 + p, p2 + p)
operator fun Line.minus(p: Vector2) = Line(p1 - p, p2 - p)
operator fun Line.times(p: Vector2) = Line(p1 * p, p2 * p)
operator fun Line.div(p: Vector2) = Line(p1 / p, p2 / p)