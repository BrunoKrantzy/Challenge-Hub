
// version rapide utilisant l'algorithme BellmanFord
// résultat obtenu en 1 mn 53 s


// a = node départ, b = node arrivée, w = poids de l'edge ab
data class cEdges(val a:Int, val b:Int, val w:Int)


fun main() {

    val startTime = System.currentTimeMillis()

    val inLines = readInput("input_21")
    //val inLines = readInput("test_21")

    val nbLines = inLines.size - 1
    var poids = 0
    var maxPoids = 0
    var nbNodes = 0

    val tabCleanCol = Array(inLines.size) { IntArray(20) }
    val tabClean = Array(inLines.size) { IntArray(17) { 0 } }

    inLines.forEachIndexed { ix, string ->
        val lstV = string.splitInts()
        lstV.forEachIndexed { ixc, v ->
            tabCleanCol[ix][ixc] = v
        }
    }

    tabCleanCol.forEachIndexed { ixl, _ ->
        for (i in 0 .. 15) {
            var t = 0
            for (j in i .. i+4) {
                t += tabCleanCol[ixl][j]
            }
            tabClean[ixl][i+1] = t
        }
    }

    val mapPoidsCells = mutableMapOf<Int, Int>()
    val mapLiensCells = mutableMapOf<Int, MutableList<Int>>()
    for (lig in 0 .. nbLines) {
        for (col in 1 .. 16) {
            var numKey = (lig * 16)
            val k = numKey+col
            val p = tabClean[lig][col]
            mapPoidsCells[k] = p

            mapLiensCells[k] = mutableListOf()
            if (lig < nbLines) {
                var ligSuiv = lig + 1
                numKey = (ligSuiv * 16)
                if (col == 1) {
                    mapLiensCells[k]!!.add(numKey+col)
                    mapLiensCells[k]!!.add(numKey+col+1)
                } else if (col == 16) {
                    mapLiensCells[k]!!.add(numKey+col-1)
                    mapLiensCells[k]!!.add(numKey+col)
                } else {
                    mapLiensCells[k]!!.add(numKey+col-1)
                    mapLiensCells[k]!!.add(numKey+col)
                    mapLiensCells[k]!!.add(numKey+col+1)
                }
            }
        }
    }

    nbNodes = mapPoidsCells.size

    val mapGrid = mutableMapOf<Int, MutableMap<Int, Int>>()
    mapLiensCells.forEach { k, lst ->
        val pLst = mutableMapOf<Int, Int>()
        lst.forEach {
            val p = mapPoidsCells[it]
            pLst[it] = p!!
        }
        mapGrid[k] = pLst
    }

    val lstEdges = mutableListOf<cEdges>()
    mapGrid.forEach { key, lst ->
        lst.forEach { dest ->
            lstEdges.add(cEdges(key, dest.key, dest.value))
        }
    }


    fun BellmanFord(distance:IntArray, x:Int, edges:MutableList<cEdges>) : IntArray {
        distance[x] = mapPoidsCells[x]!!
        for (i in x until distance.size) {
            for (j in 0 until edges.size) {
                var (a, b, w) = edges[j]
                //distance[b] = minOf(distance[b], distance[a] + w)
                distance[b] = maxOf(distance[b], distance[a] + w)
            }
        }
        return distance
    }

    val tabDistance = IntArray(nbNodes+1) { -1 }

    for (i in 1 .. 16) {
        for (j in 1..16) {
            val tabResult = BellmanFord(tabDistance, i, lstEdges)
            val out = (nbLines * 16) + j
            poids = tabResult[out]
            maxPoids = maxOf(poids, maxPoids)
        }
    }

    println("\n$maxPoids")

    val endTime = System.currentTimeMillis()
    val processTime = endTime - startTime
    System.err.println("Completed in $processTime ms")
}
