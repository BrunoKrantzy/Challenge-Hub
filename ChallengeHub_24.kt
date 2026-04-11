
fun main() {

    val inLines = readInput("input_24")
    //val inLines = readInput("test_24")

    val ligA = inLines[0]
    val ligB = inLines[1]

    val mapCharVal = mutableMapOf<Char, Int>()
    val mapCharBin = mutableMapOf<Char, String>()

    ligA.forEach {
        if (mapCharVal.contains(it)) {
            mapCharVal[it] = mapCharVal[it]!!.plus(1)
        }
        else {
            mapCharVal[it] = 1
            mapCharBin[it] = ""
        }
    }

    var lstGrpChars = mutableListOf<Pair<String, Int>>()
    mapCharVal.forEach { ch, i ->
        lstGrpChars.add(ch.toString() to i)
    }

    val comparatorA = compareBy<Pair<String, Int>> { it.second }.thenBy { it.first }
    val comparatorB = compareBy<Pair<String, Int>> { it.second }

    lstGrpChars = lstGrpChars.sortedWith(comparatorA).toMutableList()

    val mapGrpCharNivo = mutableMapOf<String, Int>()

    while (lstGrpChars.size > 1) {
        val v1 = lstGrpChars[0].first
        val v2 = lstGrpChars[1].first
        mapGrpCharNivo[v1] = 0
        mapGrpCharNivo[v2] = 1
        val duoStr = v1 + v2
        val v11 = lstGrpChars[0].second
        val v22 = lstGrpChars[1].second
        val duoInt = v11 + v22
        lstGrpChars.removeAt(1)
        lstGrpChars.removeAt(0)
        lstGrpChars.add(Pair(duoStr, duoInt))
        lstGrpChars = lstGrpChars.sortedWith(comparatorB).toMutableList()
    }

    mapCharBin.forEach {
        var bin = ""
        mapGrpCharNivo.forEach { group ->
            if (group.key.contains(it.key)) {
                bin += group.value.toString()
            }
        }
        mapCharBin[it.key] = bin.reversed()
    }

    val mapBinChar = mutableMapOf<String, Char>()
    mapCharBin.forEach {
        mapBinChar[it.value] = it.key
    }

    val result = StringBuilder()
    var code = ""
    var pos = -1

    while (pos < ligB.length-1) {
        while (true) {
            pos++
            code += ligB[pos]
            if (mapBinChar.containsKey(code)) {
                result.append(mapBinChar[code])
                code = ""
                break
            }
        }
    }

    println(result)
}

// Some random characters: (]!~`^`.'+>'%"|.*:@)}?"^;;%#+-}#{-;+}>-=%:?->*$:<} The actual answer is: churlish
// réponse à envoyer : churlish
