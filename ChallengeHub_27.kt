
fun main() {

    val inLines = readInput("input_27")
    //val inLines = readInput("test_27")

    val nbL = inLines.size + 2
    val nbC = inLines[0].length + 2

    val tabGame = Array(nbL) { Array(nbC) { ' ' } }
    inLines.forEachIndexed { ixL, line ->
        line.forEachIndexed { ixC, car ->
            tabGame[ixL+1][ixC+1] = car
        }
    }

    val lstPosDep = mutableListOf<Pair<Int, Int>>()
    for (l in 1 until nbL-1) {
        for (c in 1 until nbC-1) {
            var nbVoisins = 0
            if (tabGame[l][c] != ' ') {
                if (tabGame[l][c-1] != ' ') nbVoisins++
                if (tabGame[l][c+1] != ' ') nbVoisins++
                if (tabGame[l-1][c] != ' ') nbVoisins++
                if (tabGame[l+1][c] != ' ') nbVoisins++
                if (nbVoisins == 1) lstPosDep.add(l to c)
            }
        }
    }
    lstPosDep.sortBy { it.second }

    val lstWords = mutableListOf<String>()
    var word = ""
    var dirOld = ""
    var dirNew = ""

    fun findNewDir(p:Pair<Int, Int>, dirOld:String) : Pair<String, Pair<Int, Int>> {
        var dirPos = Pair("", Pair(0, 0))
        if (dirOld != "E" && tabGame[p.first][p.second-1] != ' ') dirPos = Pair("W", Pair(p.first, p.second-1))
        if (dirOld != "W" && tabGame[p.first][p.second+1] != ' ') dirPos = Pair("E", Pair(p.first, p.second+1))
        if (dirOld != "S" && tabGame[p.first-1][p.second] != ' ') dirPos = Pair("N", Pair(p.first-1, p.second))
        if (dirOld != "N" && tabGame[p.first+1][p.second] != ' ') dirPos = Pair("S", Pair(p.first+1, p.second))
        return dirPos
    }

    fun snakeWords(dep:Pair<Int, Int>) : Pair<Int, Int> {
        var lastPos = dep
        var endSnake = false
        word += tabGame[dep.first][dep.second]
        while (!endSnake) {
            var notFound = true
            when (dirOld) {
                "N" -> {
                    if (tabGame[lastPos.first - 1][lastPos.second] != ' ') {
                        lastPos = Pair(lastPos.first - 1, lastPos.second)
                        word += tabGame[lastPos.first][lastPos.second]
                        notFound = false
                    }
                }
                "S" -> {
                    if (tabGame[lastPos.first + 1][lastPos.second] != ' ') {
                        lastPos = Pair(lastPos.first + 1, lastPos.second)
                        word += tabGame[lastPos.first][lastPos.second]
                        notFound = false
                    }
                }
                "E" -> {
                    if (tabGame[lastPos.first][lastPos.second + 1] != ' ') {
                        lastPos = Pair(lastPos.first, lastPos.second + 1)
                        word += tabGame[lastPos.first][lastPos.second]
                        notFound = false
                    }
                }
                "W" -> {
                    if (tabGame[lastPos.first][lastPos.second - 1] != ' ') {
                        lastPos = Pair(lastPos.first, lastPos.second - 1)
                        word += tabGame[lastPos.first][lastPos.second]
                        notFound = false
                    }
                }
            }

            if (notFound) {
                dirNew = findNewDir(lastPos, dirOld).first
                if (dirNew.isEmpty())
                    endSnake = true
            }

            if (dirNew.isNotEmpty() && dirNew != dirOld) {
                lstWords.add(word)
                word = tabGame[lastPos.first][lastPos.second].toString()
                dirOld = dirNew
            }

            if (endSnake) {
                lstWords.add(word)
                word = ""
            }
        }

        return lastPos
    }

    val lstPosVues = mutableListOf<Pair<Int, Int>>()
    lstPosDep.forEach { p ->
        if (!lstPosVues.contains(p)) {
            lstPosVues.add(p)
            val newPosDir = findNewDir(p, "")
            dirOld = newPosDir.first
            val lastPos = snakeWords(p)
            lstPosVues.add(lastPos)
        }
    }

    var result = 0L
    lstWords.forEach { word ->
        val nbL = word.length
        var sumWord = 0L
        word.forEach { let ->
            sumWord += let.code - 96
        }
        result += sumWord * nbL
    }


    println(result)
}
