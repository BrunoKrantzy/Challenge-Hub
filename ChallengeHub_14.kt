
fun main() {

    val inLines = readInput("input_14")
    //val inLines = readInput("test_14")

    var totalTours = 0

    val mapBingo = mutableMapOf<Int, Pair<Int,Int>>()
    val inBingo = readInput("bingo_14")

    inBingo.forEachIndexed { ix, line ->
        val lst = line.splitInts()
        lst.forEachIndexed { li, no ->
            mapBingo[no] = Pair(ix, li)
        }
    }

    fun scanTablo(tablo: Array<Array<Int>>) : Boolean {
        var win = true
        // horizontal
        for (l in 0 until 5) {
            win = true
            for (c in 0 until 5) {
                if (tablo[l][c] == 0)
                    win = false
            }
            if (win) return win
        }
        // vertical
        for (c in 0 until 5) {
            win = true
            for (l in 0 until 5) {
                if (tablo[l][c] == 0)
                    win = false
            }
            if (win) return win
        }
        // diag 1
        win = true
        var pos = -1
        for (c in 0 until 5) {
            pos++
            if (tablo[pos][pos] == 0)
                win = false
        }
        if (win) return win
        // diag 2
        win = true
        var posL = -1
        var posC = 5
        for (c in 0 until 5) {
            posL++
            posC--
            if (tablo[posL][posC] == 0)
                win = false
        }

        return win
    }

    inLines.forEach { line ->
        val lstLine = line.splitInts()
        val tabBingo = Array(5) { Array(5) { 0 } }
        var localTour = 0

        for (cp in 0 until lstLine.size) {
            val num = lstLine[cp]
            localTour++
            if (mapBingo.containsKey(num))  {
                val l = mapBingo[num]!!.first
                val c = mapBingo[num]!!.second
                tabBingo[l][c] = 1

                if (cp >= 4) {
                    val gain = scanTablo(tabBingo)
                    if (gain) {
                        totalTours += localTour
                        break
                    }
                }
            }
        }
    }

    println("Solution : $totalTours")
}
