import kotlin.math.pow

data class Match(val id:Int) {
    var joueurA = ""
    var joueurB = ""
    var scoreA = 0
    var scoreB = 0

    fun vainqueur(): String {
        if (scoreA > scoreB)
            return "A"
        else if (scoreA < scoreB)
            return "B"
        else
            return "ex aequo"
    }
}

fun main() {

    val inLines = readInput("input_7")
    //val inLines = readInput("test_7")

    val lstELO = mutableMapOf<String, Double>()
    val lstMatches = mutableListOf<Match>()

    inLines.forEachIndexed { ix, it ->
        val lst = it.split(",")
        val j1 = lst[0]
        val j2 = lst[1]
        val score = lst[2].split("-")
        val scoreA = score[0]
        val scoreB = score[1]

        val m = Match(ix)
        m.joueurA = j1
        m.joueurB = j2
        m.scoreA = scoreA.toInt()
        m.scoreB = scoreB.toInt()

        lstMatches.add(m)
        lstELO[m.joueurA] = 1200.0
        lstELO[m.joueurB] = 1200.0
    }

    lstMatches.forEach {
        val vainqueur = it.vainqueur()
        var eloA = lstELO[it.joueurA]!!
        var eloB = lstELO[it.joueurB]!!

        val eA = 1 / (1 + (10.0.pow(((eloB-eloA)/400))))
        val eB = 1 / (1 + (10.0.pow(((eloA-eloB)/400))))

        if (vainqueur == "A") {
            eloA += (20 * (1 - eA))
            eloB -= (20 * (1 - eA))
        }
        else {
            eloA -= (20 * (1 - eB))
            eloB += (20 * (1 - eB))
        }
        lstELO[it.joueurA] = eloA
        lstELO[it.joueurB] = eloB
    }

    val maxELO = lstELO.values.max().toInt()
    val minELO = lstELO.values.min().toInt()
    val dif = (maxELO - minELO)

    println("Result = $maxELO-$minELO")
    println("Dif = $dif")
}

// 1238-1161 : OK 77

