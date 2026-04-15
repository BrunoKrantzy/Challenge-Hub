import java.util.Collections.swap

fun main() {

    val inLines = readInput("input_26")
    //val inLines = readInput("test_26")

    var result = 0L

    fun <V> List<V>.permutationsSet(): List<List<V>> {
        val retVal: MutableList<List<V>> = mutableListOf()

        fun generate(k: Int, list: List<V>) {
            if (k == 1) {
                if (!retVal.contains(list.toList()))
                    retVal.add(list.toList())
            } else {
                for (i in 0 until k) {
                    generate(k - 1, list)
                    if (k % 2 == 0) {
                        swap(list, i, k - 1)
                    } else {
                        swap(list, 0, k - 1)
                    }
                }
            }
        }

        generate(this.count(), this.toList())
        return retVal
    }

    inLines.forEach { line ->
        val lg = line.length-1
        var v = line[lg]
        var pos = lg
        var valInit = 0
        var valNew = 0

        while (pos > 0) {
            pos--
            val vPos = line[pos]
            if (vPos < v) {
                valInit = line.substring(pos, lg+1).toInt()

                val lineList = mutableListOf<Int>()
                val lineEnd = line.substring(pos)
                lineEnd.forEach { n ->
                    lineList.add(n.code - 48)
                }
                val lstPermutations = lineList.permutationsSet()
                val lstNombres = mutableListOf<Int>()
                lstPermutations.forEach { lst ->
                    val n = lst.joinToString("").toInt()
                    lstNombres.add(n)
                }
                lstNombres.sort()
                val p = lstNombres.indexOf(valInit)
                valNew = lstNombres[p+1]

                break
            }
            else {
                v = vPos
            }
        }

        result += valNew - valInit
    }

    println("\nGain total : $result")
}

//  = 11923911
