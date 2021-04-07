package fuzzyMath.components

import java.awt.Point
import kotlin.math.*

class Vector2(var x: Double, var y: Double): Shape {

    val abs get() = sqrt(x * x + y * y)
    val angle get() = atan2(y, x)

    fun distance(pt: Vector2) = (this - pt).abs

    fun firstQuadrant() =
        Vector2(abs(x), abs(y))

    fun toPoint() = Point(x.toInt(), y.toInt())

    fun reflect(angle: Double) = this * fromPolar(1.0, (angle - this.angle) * 2)

    fun unitVector(): Vector2 {
        val r = abs
        return if (r == 0.0) zero() else this / r
    }

    fun reciprocal() = Vector2(x,-y) / (x * x + y * y)

    fun new() = Vector2(x,y)

    fun squared() = this * this

    companion object {
        fun fromPoint(p: Point) = Vector2(p.x.toDouble(), p.y.toDouble())
        fun fromPolar(r: Double, theta: Double) = Vector2(r * cos(theta), r * sin(theta))
        fun zero() = Vector2(0.0, 0.0)
        fun left() = Vector2(-1.0,0.0)
        fun right() = Vector2(1.0,0.0)
        fun up() = Vector2(0.0,-1.0)
        fun down() = Vector2(0.0,1.0)
    }
}

operator fun Vector2.plus(p2: Vector2) = Vector2(x + p2.x, y + p2.y)
operator fun Vector2.plus(r: Double) = Vector2(x + r, y)
operator fun Vector2.plus(i: Int) = Vector2(x + i, y)
operator fun Vector2.minus(p2: Vector2) = Vector2(x - p2.x, y - p2.y)
operator fun Vector2.minus(r: Double) = Vector2(x - r, y)
operator fun Vector2.minus(i: Int) = Vector2(x - i, y)
operator fun Vector2.times(d: Double) = Vector2(x * d, y * d)
operator fun Vector2.times(i: Int) = Vector2(x * i, y * i)
operator fun Vector2.times(v: Vector2) = Vector2(x * v.x - y * v.y, x * v.y + y * v.x)
operator fun Vector2.div(d: Double) = Vector2(x / d, y / d)
operator fun Vector2.div(i: Int) = Vector2(x / i, y / i)
operator fun Vector2.div(v: Vector2) = this * v.reciprocal()


