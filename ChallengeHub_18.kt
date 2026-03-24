import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun main() {

    val inLines = readInput("input_18")
    //val inLines = readInput("test_18")

    var result = 0L

    fun isPal(string: String): Boolean {
        val testString = string.lowercase(Locale.getDefault())
            .replace("""[\W+]""".toRegex(), "")
        return testString == testString.reversed()
    }

    for (it in inLines) {

        if (isPal(it)) {
            println("Palindrome en entrée : $it")
            continue
        }

        val timeIn = LocalTime.parse(it)
        var timeOutPlus = timeIn
        var timeOutMoins = timeIn
        var locResult = 0

        for (sec in 1 .. 36000) {
            timeOutMoins = timeOutMoins.minusSeconds(1L)
            var newTime = timeOutMoins.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            if (isPal(newTime)) {
                locResult = sec
                break
            }

            timeOutPlus = timeOutPlus.plusSeconds(1L)
            newTime = timeOutPlus.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            if (isPal(newTime)) {
                locResult = sec
                break
            }
        }
        result += locResult
    }

    println("Solution : $result")
}
