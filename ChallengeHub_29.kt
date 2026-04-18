
fun main() {

    val inLines = readInput("input_29")
    //val inLines = readInput("test_29")

    val end = inLines[0].toInt()
    var nbGood = 0

    for (i in 0 .. end) {
        val strI = i.toString()
        var good = true
        var oldCar = -1
        for (car in strI) {
            val cInt = car.code - 48
            if (cInt < oldCar) {
                good = false
                break
            }
            else
                oldCar = cInt
        }
        if (good) nbGood++
    }

    println(nbGood)
}
