
class ShortestPath (nbV: Int) {

    private val nVertex = nbV

    fun minDistance(dist: IntArray, sptSet: Array<Boolean?>): Int {
        // Initialize min value
        var min = Int.MAX_VALUE
        var minIndex = -1
        for (v in 0 until nVertex) if (sptSet[v] == false && dist[v] <= min) {
            min = dist[v]
            minIndex = v
        }
        return minIndex
    }

    // A utility function to print the constructed distance array
    fun printSolution(dist: IntArray) {
        println("Vertex \t\t Distance from Source")
        for (i in 0 until nVertex) println(i.toString() + " \t\t " + dist[i])
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix representation
    fun dijkstra(graph: Array<IntArray>, src: Int) : IntArray {
        val dist = IntArray(nVertex)
        // The output array. dist[i] will hold the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        val sptSet = arrayOfNulls<Boolean>(nVertex)

        // Initialize all distances as INFINITE and stpSet[] as false
        for (i in 0 until nVertex) {
            dist[i] = Int.MAX_VALUE
            sptSet[i] = false
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0

        // Find shortest path for all vertices
        for (count in 0 until nVertex - 1) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first iteration.
            val u = minDistance(dist, sptSet)

            // Mark the picked vertex as processed
            sptSet[u] = true

            // Update dist value of the adjacent vertices of the picked vertex.
            for (v in 0 until nVertex)
            // Update dist[v] only if is not in sptSet, there is an edge from u to v, and total
            // weight of path from src to v through u is smaller than current value of dist[v]
                if (!sptSet[v]!! && graph[u][v] != 0 && dist[u] != Int.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v]
        }

        // return the constructed distance array
        return dist
    }
}


fun main() {

    val inLines = readInput("input_10")
    //val inLines = readInput("test_10")

    val set1 = mutableSetOf<String>()
    val mapUsers = mutableMapOf<String, Int>()
    var nUser = 0

    inLines.forEach {
        val (n1, n2, prix) = it.split(",")

        if (!mapUsers.containsKey(n1)) {
            nUser++
            mapUsers[n1] = nUser
        }
        if (!mapUsers.containsKey(n2)) {
            nUser++
            mapUsers[n1] = nUser
        }

        val str = "$n2,$n1,$prix"
        if (!set1.contains(str))
            set1.add("$n1,$n2,$prix")
    }

    val graph: Array<IntArray> = Array (nUser + 1) { IntArray(set1.size * 2) { 0 } }
    set1.forEach {
        val (n1, n2, p) = it.split(",")
        val s1 = mapUsers[n1]!!
        val s2 = mapUsers[n2]!!
        val prix = p.toInt()
        graph[s1][s2] = prix
        graph[s2][s1] = prix
    }

    val obShortPath = ShortestPath(nUser + 1)
    val taBDist = obShortPath.dijkstra(graph, mapUsers["TUPAC"]!!)
    val cible = mapUsers["DIDDY"]!!

    println(taBDist[cible])
}
