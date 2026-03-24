
fun main() {

    val inLines = readInput("input_15")
    //val inLines = readInput("test_15")
    val lstWords = readInput("lstwords_15").sorted().toMutableList()

    fun bfs(start:String, end:String) : Int {
        val visited = mutableMapOf<String, Int>()
        visited[start] = 1
        val q = ArrayDeque<String>()
        q.add(start)

        while (q.isNotEmpty()) {
            val word = q.first()
            if (word == end) {
                break
            }
            q.removeFirst()
            val len = visited[word]
            for (i in 0 until word.length) {
                for (j in 0 until 26) {
                    val next = word.toCharArray()
                    next[i] = 'a' + j
                    if (next[i] == word[i]) {
                        continue
                    }
                    val nextWord = next.joinToString("")
                    if (lstWords.binarySearch(nextWord) < 0) {
                        continue
                    }
                    if (!visited.containsKey(nextWord)) {
                        visited[nextWord] = len!!.plus(1)
                        q.add(nextWord)
                    }
                }
            }
        }

        if (visited.containsKey(end)) {
            return visited[end]!!
        }

        return Int.MAX_VALUE
    }

    var rep = 1
    inLines.forEach { line ->
        val start = line.substringBefore(",")
        val end = line.substringAfter(",")
        rep *= bfs(start, end)
    }

    println("Solution = $rep")
}

// 97920000

