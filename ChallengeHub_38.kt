
fun findStreak(numbers: List<Int>, ix: Int, curStreakLength: Int): Boolean {
    for (start in (ix - curStreakLength + 1)..ix) {
        if (start < 0 || start + curStreakLength > numbers.size) {
            continue
        }
        val sum = (0..<curStreakLength).sumOf { i -> numbers[start + i] }
        if (sum % curStreakLength == 0) {
            return true
        }
    }
    return false
}

fun findLongestStreak(numbers: List<Int>, ix: Int, maxStreak: Int): Int {
    for (curStreakLength in 2..maxStreak) {
        if (!findStreak(numbers, ix, curStreakLength)) {
            return curStreakLength - 1
        }
    }
    return maxStreak
}

fun comfortable(numbers: List<Int>): Int {
    val maxStreak = numbers.size
    return (0..<maxStreak).sumOf { ix ->
        findLongestStreak(numbers, ix, maxStreak)
    }
}


fun main() {
    var rep = 0
    val inLines = readInput("input_38")
    //val inLines = readInput("test_38")

    inLines.forEach { lig ->
        rep += comfortable(lig.splitInts())
    }

    println(rep)
}

// == 131086
