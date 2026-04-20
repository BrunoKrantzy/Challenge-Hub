
fun main() {

    val inLines = readInput("input_30")
    //val inLines = readInput("test_30")

    var result = 0

    inLines.forEach { line ->
        val nb1 = line.filter { it == '1' }.count()
        if (nb1 % 2 != 0) {
            result += (nb1 / 2) + 1
        }
    }

    println(result)
}
