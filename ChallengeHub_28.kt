
fun main() {

    val inLines = readInput("input_28")
    //val inLines = readInput("test_28")

    var strToDecode = "FISSION_MAILED"
    var strOut = ""

    var tabGame = Array(inLines.size) { Array(inLines[0].length) { ' ' } }

    inLines.forEachIndexed { ixL, line ->
        line.forEachIndexed { ixC, char ->
            tabGame[ixL][ixC] = char
        }
    }

    fun changeDir(dir: String, car: Char) : String {
        var newDir = ""
        when (dir) {
            "N" -> if (car == '/')
                newDir = "R"
            else newDir = "L"

            "S" -> if (car == '/')
                newDir = "L"
            else newDir = "R"

            "L" -> if (car == '/')
                newDir = "S"
            else newDir = "N"

            "R" -> if (car == '/')
                newDir = "N"
            else newDir = "S"
        }
        return newDir
    }

    strToDecode.forEach { letter ->
        var dir = "R"
        var posDep: Pair<Int, Int>
        if (letter == '_')
            posDep = Pair(27, 0)
        else
            posDep = Pair(letter.code - 64, 0)

        var notFound = true
        while (notFound) {
            when (dir) {
                "N" -> posDep = Pair(posDep.first-1, posDep.second)
                "S" -> posDep = Pair(posDep.first+1, posDep.second)
                "R" -> posDep = Pair(posDep.first, posDep.second+1)
                "L" -> posDep = Pair(posDep.first, posDep.second-1)
            }

            val c = tabGame[posDep.first][posDep.second]
            if (c == '/') {
                tabGame[posDep.first][posDep.second] = '\\'
                dir = changeDir(dir, c)
            }
            else if (c == '\\') {
                tabGame[posDep.first][posDep.second] = '/'
                dir = changeDir(dir, c)
            }
            else if (c != ' ') {
                notFound = false
                strOut += c
            }
        }
    }

    println(strOut)
}
