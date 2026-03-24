
fun main() {

    val inLines = readInput("input_13")
    //val inLines = readInput("test_13")

    var rep = 0

    inLines.forEach { line ->
        val lstChars = mutableListOf<Char>()
        line.forEach { c ->
            lstChars.add(c)
        }
        var vPat = 0
        var prodLV = 0
        var maxP1 = 0
        val lgLine = line.length

        for (i in 0 until lgLine) {
            for (j in i+2 .. lgLine) {
                val p1 = line.substring(i, j)
                val lineSplit = line.replace(p1.toRegex(), "@")

                val lstP1 = lineSplit.filter { it == '@' }.count()
                if (lstP1 > 1 && p1.length > 1) {
                    maxP1 = maxOf(maxP1, lstP1)
                    if (lstP1 * p1.length > prodLV) {
                        vPat = lstP1
                        prodLV = lstP1 * p1.length
                    }
                }
                else if (p1.length > 1 && p1.groupingBy { it }.eachCount().size == 1) {
                    if (lstP1 * p1.length >= prodLV) {
                        vPat = p1.length
                        prodLV = lstP1 * p1.length
                    }
                }
            }
        }

        rep += vPat
    }

    println("Solution : $rep")
}
