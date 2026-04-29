
fun main() {

    val inLines = readInput("input_33")
    //val inLines = readInput("test_33")

    val setPP = mutableSetOf<Int>()
    for (i in 1 .. 20) {
        setPP.add(i)
        setPP.add(i * 2)
        setPP.add(i * 3)
    }
    setPP.add(25)
    setPP.add(50)

    var nbDarts = 20
    val score = inLines[0].toInt()

    for (i in 21 .. score) {
        var vMin = Int.MAX_VALUE
        var temV1 = false
        var temV2 = false
        for (j in 60 downTo 1) {
            var v1 = Int.MAX_VALUE
            var v2 = Int.MAX_VALUE
            if (i >= j && setPP.contains(j)) {
                val n = i / j
                val r = i % j
                if (r == 0) {
                    temV1 = true
                    v1 = minOf(v1, n)
                }
                else if (setPP.contains(r)) {
                    temV1 = true
                    v1 = minOf(v1, n + 1)
                }
                else {
                    for (k in 60 downTo 1) {
                        if (r >= k && setPP.contains(k)) {
                            if (r % k == 0) {
                                temV2 = true
                                if (k > 1)
                                    v2 = minOf(v2, n + 1)
                                else
                                    v2 = minOf(v2, n + 2)
                            }
                        }
                    }
                }
                if (temV2) {
                    vMin = minOf(vMin,v2)
                    break
                }
                else if (temV1) {
                    vMin = minOf(vMin, v1)
                    break
                }
            }
        }
        nbDarts += vMin
    }
    println(nbDarts)
}

//  = 503234559

