package fuzzyMath

import fuzzyMath.components.*

fun isColliding(a: Shape, b: Shape): Boolean {

    // i know this looks silly, but its using typechecks to force smartcast to the correct check method
    when (a){
        is Line -> {
            when (b) {
                is Line -> return check(a,b)
            }
        }
    }

    return false
}

private fun check(a: Line, b: Line): Boolean {
    val intercept = ((b - a.p1) / a.p2Relative).xIntercept
    return if (intercept == null) false else intercept >= 0 && intercept <= 1
}