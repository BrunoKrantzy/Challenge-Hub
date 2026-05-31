import kotlin.math.abs
import kotlin.random.Random

fun main() {

    val inLines = readInput("input_36")
    //val inLines = readInput("test_36")

    var rep = 0
    var lstL1 = mutableListOf<Int>()
    var lstL2 = mutableListOf<Int>()
    val mapL1 = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    val lstPossiblePairs = mutableListOf<Pair<Int, Int>>()
    var lstTemoinL2 = listOf<Int>()

    fun solve(lstPairs: MutableList<Pair<Int, Int>>, l2: MutableList<Int>) : Int {
        val lstFinalPairs = mutableListOf<Pair<Int, Int>>()
        var totalDif = 0

        while (true) {
            var isFound = false
            var nbJokers = 16 - l2.size
            val lstJokers = arrayListOf<Int>()
            val setListesVues = mutableSetOf< MutableList<Pair<Int, Int>>>()
            lstFinalPairs.clear()

            val randomValues = List(8) { Random.nextInt(0, lstPossiblePairs.size) }
            randomValues.forEach {
                lstFinalPairs.add(lstPairs[it])
            }

            val l1Temp = mutableListOf<Int>()
            val l2Temp = mutableListOf<Int>()
            l1Temp.addAll(lstL1)
            l2Temp.addAll(lstL2)

            if (!setListesVues.contains(lstFinalPairs)) {
                for (p in lstFinalPairs) {
                    val somme = p.first + p.second
                    val produit = p.first * p.second

                    if (l1Temp.contains(somme) && l1Temp.contains(produit)) {
                        l1Temp.remove(somme)
                        l1Temp.remove(produit)
                    }
                    else
                        break

                    if (l2Temp.contains(p.first)) {
                        l2Temp.remove(p.first)
                        lstJokers.add(p.first)
                    }
                    else {
                        if (nbJokers >= 1) {
                            nbJokers -= 1
                            lstJokers.add(p.first)
                        }
                        else
                            break
                    }

                    if (l2Temp.contains(p.second)) {
                        l2Temp.remove(p.second)
                        lstJokers.add(p.second)
                    }
                    else {
                        if (nbJokers >= 1) {
                            nbJokers -= 1
                            lstJokers.add(p.second)
                        }
                        else
                            break
                    }
                }

                if (l1Temp.isEmpty() && l2Temp.isEmpty()) {
                    isFound = true
                    var okSort = true
                    lstJokers.sort()
                    lstTemoinL2.forEachIndexed { ix, v ->
                        if (v > -1) {
                            if (lstJokers[ix] != v)
                                okSort = false
                        }
                    }
                    if (!okSort)
                        isFound = false
                    else {
                        println("g:$lstL1")
                        println("i:$lstL2")
                        println("${lstFinalPairs.sortedBy { it.first }}")
                        println()
                    }
                }
            }
            setListesVues.add(lstFinalPairs.toMutableList())

            if (isFound)
                break
        }

        lstFinalPairs.forEach {
            totalDif += abs(it.first - it.second)
        }

        return totalDif
    }


    // g:55 285 27 323 22 400 20 49 40 336 50 98 36 12 96 294
    // i:* 2 3 * * 7 * * 17 19 20 * * 42 48 *

    val nbL = inLines.size
    for (l in 0 until nbL step 3) {
        val l1 = inLines[l]
        val l2 = inLines[l+1]

        var l2clean = l2.substringAfter(":")
        l2clean = l2clean.replace("*", "-1")
        lstTemoinL2 = l2clean.splitInts()

        lstL1 = l1.splitInts().sorted().toMutableList()
        lstL2 = l2.splitInts().sorted().toMutableList()

        // recenser toutes les paires fonctionnelles
        mapL1.clear()
        lstPossiblePairs.clear()
        lstL1.forEach {
            if (!mapL1.containsKey(it)) {
                for (i in 1 .. it) {
                    if (it % i == 0) {
                        val v1 = minOf(it/i, i)
                        val v2 = maxOf(it/i, i)
                        if (lstL1.contains(v1 + v2) && lstL1.contains(v1 * v2)) {
                            if (!lstPossiblePairs.contains(Pair(v1, v2)))
                                lstPossiblePairs.add(Pair(v1, v2))
                        }
                    }
                }
            }
        }

        rep += solve(lstPossiblePairs, lstL2)
    }

    println(rep)
}
