package fuzzyMath

import fuzzyMath.components.*
import kotlin.math.*

public const val PHI: Double = 1.6180339887498948482045868343656381177203091798057628621354486227

fun lerp(min: Double, max: Double, value: Double) = min + (value * (max - min))
fun alerp(min: Double, max: Double, value: Double) = (value - min) / (max - min)

fun lerp(min: Vector2, max: Vector2, value: Double) = min + ((max - min) * value)

fun cos(v: Vector2) = Vector2(cos(v.x)*cosh(v.y),-sin(v.x)*sinh(v.y))
fun sin(v: Vector2) = Vector2(sin(v.x)*cosh(v.y),+cos(v.x)*sinh(v.y))

fun cpow(r: Double, C: Vector2): Vector2 {
    var lnR = Vector2(ln(r.absoluteValue),0.0)
    if (r < 0) lnR += Vector2(0.0, PI)
    val eExponent = C * lnR
    val eUpeExponentReal = E.pow(eExponent.x)
    val real = eUpeExponentReal * cos(eExponent.y)
    val imaginary = eUpeExponentReal * sin(eExponent.y)
    return Vector2(real, imaginary)
}

private const val sqrt5 = 2.2360679774997896964091736687312762354406183596115257242708972454
fun binet(n: Vector2): Vector2 {
//    return (cpow(1+sqrt5,n)-cpow(1-sqrt5,n))/(cpow(2.0,n) * sqrt5)


    val a = cpow(PHI, n)
    val b = cpow(-PHI, n * -1)
    return (a - b) / sqrt5
}