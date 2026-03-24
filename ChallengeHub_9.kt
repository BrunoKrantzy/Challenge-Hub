
fun main() {

    val inLines = readInput("input_9")
    //val inLines = readInput("test_9")

    var rep = inLines[0].toBigInteger()

    for (i in 1 until inLines.size) {
        rep *= inLines[i].toBigInteger()
        //println(rep)
    }

    println(rep)
}
