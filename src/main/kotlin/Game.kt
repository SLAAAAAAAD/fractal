fun main() {
    GameFrame
    GameScreen.drawFractal()
    GameFrame.repaint()

    val ticksPerSecond = 60.0
    val nanosecondsPerTick = 1000000000 / ticksPerSecond

    var cachedTime = System.nanoTime()
    var delta = 0.0

    while (true) {
        val now = System.nanoTime()
        delta += (now - cachedTime)
        cachedTime = now
        while (delta >= nanosecondsPerTick){
//            GameScreen.update(delta)
            delta -= nanosecondsPerTick
        }

        GameFrame.repaint()
    }
}
