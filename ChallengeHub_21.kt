// voir https://challenges.aquaq.co.uk/challenge/21
// la recherche de tous les paths possibles plante avec getAllPaths de jGrapht
// cette solution fonctionne mais prend presque 20 minutes sur les 500 lignes de l'input
// la version BellmanFord de ChallengeHub_21-2.kt, beaucoup plus rapide, ne met que 1 mn 53 s !

fun main() {

    val startTime = System.currentTimeMillis()

    val inLines = readInput("input_21")
    //val inLines = readInput("test_21")

    val nbLines = inLines.size - 1
    var poids = 0
    var maxPoids = 0

    val tabCleanCol = Array(inLines.size) { IntArray(20) }
    val tabClean = Array(inLines.size) { IntArray(18) { 0 } }

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

    val mapPoidsCells = mutableMapOf<String, Int>()
    val mapLiensCells = mutableMapOf<String, MutableList<String>>()
    for (lig in 0 .. nbLines) {
        for (col in 1 .. 16) {
            val k = "L${lig}C${col}"
            val p = tabClean[lig][col]
            mapPoidsCells[k] = p

            mapLiensCells[k] = mutableListOf()
            if (lig < nbLines) {
                var ligSuiv = lig + 1
                if (col == 1) {
                    mapLiensCells[k]!!.add("L${ligSuiv}C${col}")
                    mapLiensCells[k]!!.add("L${ligSuiv}C${col + 1}")
                } else if (col == 16) {
                    mapLiensCells[k]!!.add("L${ligSuiv}C${col - 1}")
                    mapLiensCells[k]!!.add("L${ligSuiv}C${col}")
                } else {
                    mapLiensCells[k]!!.add("L${ligSuiv}C${col - 1}")
                    mapLiensCells[k]!!.add("L${ligSuiv}C${col}")
                    mapLiensCells[k]!!.add("L${ligSuiv}C${col + 1}")
                }
            }
        }
    }

    val mapGrid = mutableMapOf<String, MutableMap<String, Int>>()
    mapLiensCells.forEach { k, lst ->
        val pLst = mutableMapOf<String, Int>()
        lst.forEach {
            val p = mapPoidsCells[it]
            pLst[it] = p!!
        }
        mapGrid[k] = pLst
    }


    fun dijkstra(graph: MutableMap<String, MutableMap<String, Int>>, start: String, end: String): MutableList<String> {
        // Set of nodes already visited
        val visited = mutableSetOf<String>()

        // Set of nodes currently being considered, along with their current distance from the start node
        val nodes = ArrayDeque<Pair<Int, String>>()

        // pour une recherche de distance :
        //nodes.add(Pair(0, start))
        // pour une recherche de prix / poids... :
        nodes.add(Pair(mapPoidsCells[start]!!, start))

        // Map to store the shortest distance from the start node to each node
        val distances = mutableMapOf<String, Int>()

        // pour une recherche de distance :
        //distances[start] = 0
        // pour une recherche de prix / poids... :
        distances[start] = mapPoidsCells[start]!!

        // Map to store the previous node in the shortest path from the start node to each node
        val previous = mutableMapOf<String, String>()

        // Iterate until all nodes have been visited
        while (nodes.isNotEmpty()) {
            // Get the node with the smallest distance
            val current = nodes.removeFirst() // poll
            val currentNode = current.second
            val currentDistance = current.first

            /* commenter si l'on veut récupérer tous les paths possibles :
            // Skip the node if it has already been visited
            if (currentNode in visited) {
                continue
            }
            */

            // Mark the node as visited
            visited.add(currentNode)

            // Update the distances and previous nodes for all neighbors of the current node
            val neighbors = graph[currentNode] ?: continue
            for (neighbor in neighbors) {
                val distance = currentDistance + neighbor.value
                // si l'on cherche le parcours le + court ou le - cher :
                //if (distance < (distances[neighbor.key] ?: Int.MAX_VALUE)) {
                // si l'on cherche le parcours le + long ou le + cher :
                if (distance > (distances[neighbor.key] ?: Int.MIN_VALUE)) {
                    distances[neighbor.key] = distance
                    previous[neighbor.key] = currentNode
                    nodes.add(Pair(distance, neighbor.key))
                }
            }
        }

        // Construct the shortest path by traversing the previous nodes from the end node to the start node
        val path = mutableListOf<String>()
        var current = end
        while (current != start) {
            path.add(current)
            current = previous[current] ?: break
        }
        path.add(start)
        path.reverse()
        return path
    }

    for (i in 1 .. 16) {
        for (j in 1..16) {
            val start = "L0C${i}"
            val out = "L${nbLines}C${j}"
            poids = 0
            val path = dijkstra(mapGrid, start, out)
            path.forEach { cell ->
                poids += mapPoidsCells[cell]!!
            }
            maxPoids = maxOf(poids, maxPoids)
        }
    }

    println("\n$maxPoids")

    val endTime = System.currentTimeMillis()
    val processTime = endTime - startTime
    System.err.println("Completed in $processTime ms")
}

// == 143487
