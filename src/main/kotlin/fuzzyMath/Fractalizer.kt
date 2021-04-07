package fuzzyMath

import fuzzyMath.components.*
import kotlin.math.E
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow

object Fractalizer {
    fun mandelbrot(C: Vector2, Z: Vector2 = C, maxIterations: Int = 500, currentIteration: Int = 0): Int {
        if (currentIteration >= maxIterations) return 0
        if (Z.abs >= C.abs + 2) return currentIteration
        var Z2 = (Vector2(Z.y, Z.x)+Z)/2
        return mandelbrot(
            C, Z2 * Z2 + C, maxIterations, currentIteration + 1
        )
    }

    fun binet(C: Vector2, Z: Vector2 = C, maxIterations: Int = 100, currentIteration: Int = 0): Int {
        if (currentIteration >= maxIterations) return 0
        if (Z.abs > C.abs + 5) return currentIteration
//        if (Z.abs >= 5) return currentIteration
        return binet(C, fuzzyMath.binet(Z), maxIterations, currentIteration + 1)
    }

    fun collatz(C: Vector2, Z: Vector2 = C, maxIterations: Int = 100, currentIteration: Int = 0): Int {
        if (currentIteration >= maxIterations) return 0
        if (Z.abs >= C.abs * 3 + 2) return currentIteration
//        if (Z.abs >= 5) return currentIteration
//        val nextZ = ((Z * 7 + 2) - (cos(Z * PI) * (Z * 5 + 2))) / 4
        val ifOdd = sin(Z * PI / 2).squared() * (Z * 3 + 1)
        val ifEven = cos(Z * PI / 2).squared() * (Z / 2)
        val nextZ = ifOdd + ifEven
        return collatz(C, nextZ, maxIterations, currentIteration + 1)
    }

    fun collatz2(C: Vector2, Z: Vector2 = C, maxIterations: Int = 100, currentIteration: Int = 0): Int {
        if (currentIteration >= maxIterations) return 0
        if (Z.abs >= C.abs * 30) return currentIteration
//        if (Z.abs >= 5) return currentIteration
        val nextZ = ((Z * 7 + 2) - cpow(E, Vector2.up() * Z * PI) * (Z * 5 + 2)) / 4
//        val ifOdd = sin(Z * PI / 2).squared() * (Z * 3 + 1)
//        val ifEven = cos(Z * PI / 2).squared() * (Z / 2)
//        val nextZ = ifOdd + ifEven
        return collatz2(C, nextZ, maxIterations, currentIteration + 1)
    }
}