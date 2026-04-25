
fun main() {

    val inLines = readInput("input_32")
    //val inLines = readInput("test_32")

    var cOpen = 0
    var pOpen = 0
    var aOpen = 0
    var lastCar = "xx"

    var nbLigOk = 0
    var goodLine = true

    val lstC = mutableListOf<String>()
    val lstP = mutableListOf<String>()
    val lstA = mutableListOf<String>()

    fun scanString(str:String) : Boolean {
        var result = true
        if (str.isNotEmpty()) {
            var n = 0
            str.forEach { c ->
                when (c) {
                    '[', '(', '{' -> n++
                    ']', ')', '}' -> n--
                }
            }
            if (n > 0) result = false
        }
        return result
    }

    inLines.forEach { line ->
        cOpen = 0
        pOpen = 0
        aOpen = 0
        goodLine = true
        lastCar = "x"

        for (car in line) {
            when (car) {
                '[' -> {
                    cOpen++
                    lstC.add("")
                    lastCar = "oC"
                    if (pOpen > 0) lstP[lstP.size-1] = lstP.last()+"["
                    if (aOpen > 0) lstA[lstA.size-1] = lstA.last()+"["
                }
                ']' -> {
                    cOpen--
                    if (cOpen == -1 || lastCar == "oP" || lastCar == "oA") {
                        goodLine = false
                        break
                    }
                    if (!scanString(lstC.last())) {
                        goodLine = false
                        break
                    }
                    lstC.removeLast()
                    lastCar = "fC"
                    if (pOpen > 0) lstP[lstP.size-1] = lstP.last()+"]"
                    if (aOpen > 0) lstA[lstA.size-1] = lstA.last()+"]"
                }
                '(' -> {
                    pOpen++
                    lstP.add("")
                    lastCar = "oP"
                    if (cOpen > 0) lstC[lstC.size-1] = lstC.last()+"("
                    if (aOpen > 0) lstA[lstA.size-1] = lstA.last()+"("
                }
                ')' -> {
                    pOpen--
                    if (pOpen == -1 || lastCar == "oC" || lastCar == "oA") {
                        goodLine = false
                        break
                    }
                    if (!scanString(lstP.last())) {
                        goodLine = false
                        break
                    }
                    lstP.removeLast()
                    lastCar = "fP"
                    if (cOpen > 0) lstC[lstC.size-1] = lstC.last()+")"
                    if (aOpen > 0) lstA[lstA.size-1] = lstA.last()+")"
                }
                '{' -> {
                    aOpen++
                    lstA.add("")
                    lastCar = "oA"
                    if (cOpen > 0) lstC[lstC.size-1] = lstC.last()+"{"
                    if (pOpen > 0) lstP[lstP.size-1] = lstP.last()+"{"
                }
                '}' -> {
                    aOpen--
                    if (aOpen == -1 || lastCar == "oC" || lastCar == "oP") {
                        goodLine = false
                        break
                    }
                    if (!scanString(lstA.last())) {
                        goodLine = false
                        break
                    }
                    lstA.removeLast()
                    lastCar = "fA"
                    if (cOpen > 0) lstC[lstC.size-1] = lstC.last()+"}"
                    if (pOpen > 0) lstP[lstP.size-1] = lstP.last()+"}"
                }
            }
        }
        if (goodLine && cOpen == 0 && pOpen == 0 && aOpen == 0) nbLigOk++
    }

    println(nbLigOk)
}

// 616
