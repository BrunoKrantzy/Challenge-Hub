
fun main() {

    val inLines = readInput("input_2")
    //val inLines = readInput("test_2")

    var lstN = mutableListOf<Int>()
    lstN = inLines[0].splitInts().toMutableList()

    val lN = lstN.size
    var p1 = 0
    var p2 = 0
    val lstSup = mutableListOf<Pair<Int, Int>>()

    while (p1 < lN - 1) {
        var no = lstN[p1]
        p2 = lstN.lastIndexOf(no)

        if (p2 > p1) {
            lstSup.add(Pair(p1, p2))
            p1 = p2 + 1
        }
        else
            p1++
    }

    lstSup.reverse()
    lstSup.forEach {
        val p1 = it.first
        val p2 = it.second
        for (i in p2 downTo p1+1) {
            lstN.removeAt(i)
        }
    }

    println(lstN.sum())
}
