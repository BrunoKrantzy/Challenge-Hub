
fun main() {

    val inLines = readInput("input_12")
    //val inLines = readInput("test_12")

    var nbFloors = 1
    val maxFloor = inLines.size
    var floor = 0
    var dir = "UP"

    fun reverseDir() {
        if (dir == "UP")
            dir = "DOWN"
        else
            dir = "UP"
    }

    while (true) {
        if (floor > maxFloor)
            break
        val instructions = inLines[floor].split(" ")
        val sens = instructions[0].toInt()
        val num = instructions[1].toInt()

        if (sens == 0) reverseDir()
        if (dir == "UP")
            floor += num
        else
            floor -= num

        nbFloors++
    }

    println(nbFloors)
}
