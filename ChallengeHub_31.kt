
fun main() {

    val inLines = readInput("input_31")
    //val inLines = readInput("test_31")

    val tabU = Array(3) { Array(3) { 2 } }
    val tabL = Array(3) { Array(3) { 3 } }
    val tabF = Array(3) { Array(3) { 1 } }
    val tabR = Array(3) { Array(3) { 4 } }
    val tabD = Array(3) { Array(3) { 5 } }
    val tabB = Array(3) { Array(3) { 6 } }

    fun copyTablo(tablo: Array<Array<Int>>) : Array<Array<Int>> {
        val t = Array(3) { Array(3) { -1 } }
        for (l in 0 until tablo.size) {
            for (c in 0 until tablo.size) {
                t[l][c] = tablo[l][c]
            }
        }
        return t
    }

    fun rotaHoraire(tablo: Array<Array<Int>>) {
        val tabSaved = copyTablo(tablo)
        tablo[0][0] = tabSaved[2][0]
        tablo[0][1] = tabSaved[1][0]
        tablo[0][2] = tabSaved[0][0]
        tablo[1][0] = tabSaved[2][1]
        tablo[1][1] = tabSaved[1][1]
        tablo[1][2] = tabSaved[0][1]
        tablo[2][0] = tabSaved[2][2]
        tablo[2][1] = tabSaved[1][2]
        tablo[2][2] = tabSaved[0][2]
   }

    fun rotaAnti(tablo: Array<Array<Int>>) {
        val tabSaved = copyTablo(tablo)
        tablo[0][0] = tabSaved[0][2]
        tablo[0][1] = tabSaved[1][2]
        tablo[0][2] = tabSaved[2][2]
        tablo[1][0] = tabSaved[0][1]
        tablo[1][1] = tabSaved[1][1]
        tablo[1][2] = tabSaved[2][1]
        tablo[2][0] = tabSaved[0][0]
        tablo[2][1] = tabSaved[1][0]
        tablo[2][2] = tabSaved[2][0]
    }

    fun rU() {
        val tabSaved = tabF[0]
        tabF[0] = tabR[0]
        tabR[0] = tabB[0]
        tabB[0] = tabL[0]
        tabL[0] = tabSaved
    }

    fun rD() {
        val tabSaved = tabF[2]
        tabF[2] = tabL[2]
        tabL[2] = tabB[2]
        tabB[2] = tabR[2]
        tabR[2] = tabSaved
    }

    fun rR() {
        val c0Saved = tabU[2][2]
        val c1Saved = tabU[1][2]
        val c2Saved = tabU[0][2]
        tabU[0][2] = tabF[0][2]
        tabU[1][2] = tabF[1][2]
        tabU[2][2] = tabF[2][2]
        tabF[0][2] = tabD[0][2]
        tabF[1][2] = tabD[1][2]
        tabF[2][2] = tabD[2][2]
        tabD[2][2] = tabB[0][0]
        tabD[1][2] = tabB[1][0]
        tabD[0][2] = tabB[2][0]
        tabB[0][0] = c0Saved
        tabB[1][0] = c1Saved
        tabB[2][0] = c2Saved
    }

    fun rL() {
        val c0Saved = tabF[0][0]
        val c1Saved = tabF[1][0]
        val c2Saved = tabF[2][0]
        tabF[0][0] = tabU[0][0]
        tabF[1][0] = tabU[1][0]
        tabF[2][0] = tabU[2][0]
        tabU[0][0] = tabB[2][2]
        tabU[1][0] = tabB[1][2]
        tabU[2][0] = tabB[0][2]
        tabB[0][2] = tabD[2][0]
        tabB[1][2] = tabD[1][0]
        tabB[2][2] = tabD[0][0]
        tabD[0][0] = c0Saved
        tabD[1][0] = c1Saved
        tabD[2][0] = c2Saved
    }

    fun rF() {
        val c0Saved = tabU[2][0]
        val c1Saved = tabU[2][1]
        val c2Saved = tabU[2][2]
        tabU[2][0] = tabL[2][2]
        tabU[2][1] = tabL[1][2]
        tabU[2][2] = tabL[0][2]
        tabL[0][2] = tabD[0][0]
        tabL[1][2] = tabD[0][1]
        tabL[2][2] = tabD[0][2]
        tabD[0][0] = tabR[2][0]
        tabD[0][1] = tabR[1][0]
        tabD[0][2] = tabR[0][0]
        tabR[0][0] = c0Saved
        tabR[1][0] = c1Saved
        tabR[2][0] = c2Saved
    }

    fun rB() {
        val c0Saved = tabU[0][0]
        val c1Saved = tabU[0][1]
        val c2Saved = tabU[0][2]
        tabU[0][0] = tabR[0][2]
        tabU[0][1] = tabR[1][2]
        tabU[0][2] = tabR[2][2]
        tabR[0][2] = tabD[2][2]
        tabR[1][2] = tabD[2][1]
        tabR[2][2] = tabD[2][0]
        tabD[2][0] = tabL[0][0]
        tabD[2][1] = tabL[1][0]
        tabD[2][2] = tabL[2][0]
        tabL[2][0] = c0Saved
        tabL[1][0] = c1Saved
        tabL[0][0] = c2Saved
    }

    for (i in 0 until inLines[0].length) {
        var code = inLines[0][i].toString()
        if (code == "'")
            continue
        if (i < inLines[0].length-1 && inLines[0][i+1] == '\'') {
            code += "p"
        }
        when (code) {
            "U" -> {
                rotaHoraire(tabU)
                rU()
            }
            "Up" -> {
                rotaAnti(tabU)
                for (i in 1 .. 3) rU()
            }
            "D" -> {
                rotaHoraire(tabD)
                rD()
            }
            "Dp" ->  {
                rotaAnti(tabD)
                for (i in 1 .. 3) rD()
            }
            "R" -> {
                rotaHoraire(tabR)
                rR()
            }
            "Rp" -> {
                rotaAnti(tabR)
                for (i in 1 .. 3) rR()
            }
            "L" -> {
                rotaHoraire(tabL)
                rL()
            }
            "Lp" -> {
                rotaAnti(tabL)
                for (i in 1 .. 3) rL()
            }
            "F" -> {
                rotaHoraire(tabF)
                rF()
            }
            "Fp" -> {
                rotaAnti(tabF)
                for (i in 1 .. 3) rF()
            }
            "B" -> {
                rotaHoraire(tabB)
                rB()
            }
            "Bp" -> {
                rotaAnti(tabB)
                for (i in 1 .. 3) rB()
            }
        }
    }

    var result = 1
    for (l in 0 .. 2) {
        for (c in 0 .. 2) {
            result *= tabF[l][c]
        }
    }

    println(result)
}

// 7200

