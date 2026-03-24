
fun main() {

    val inLines = readInput("input_5")
    //val inLines = readInput("test_5")

    data class Dice(var h:Int, var b:Int, var g:Int, var d:Int, var f:Int, var k:Int) {

        fun roll(dir:String) {
            when (dir) {
                "L" -> {
                    val d1 = d
                    d = f
                    f = g
                    g = k
                    k = d1
                }
                "R" -> {
                    val d1 = d
                    d = k
                    k = g
                    g = f
                    f = d1
                }
                "U" -> {
                    val f1 = f
                    f = h
                    h = k
                    k = b
                    b = f1
                }
                "D" -> {
                    val f1 = f
                    f = b
                    b = k
                    k = h
                    h = f1
                }
            }
        }
    }

    var rep = 0
    var dice1 = Dice(3, 4, 2, 5, 1,6 )
    var dice2 = Dice(2, 5, 3, 4, 1,6 )

    inLines[0].forEachIndexed { ix, dir ->
        dice1.roll(dir.toString())
        dice2.roll(dir.toString())

        if (dice1.f == dice2.f) {
            rep += ix
        }
    }

    println(rep)
}
