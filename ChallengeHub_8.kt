fun main() {

    val inLines = readInput("input_8")
    //val inLines = readInput("test_8")

    val mapBuyMilk = mutableMapOf<Int, Int>()
    val lstMilk = mutableListOf(0)
    val lstCereal = mutableListOf(0)

    inLines.forEachIndexed { ix, str ->
        val ls = str.split(",")
        val p = ls[1].toInt()
        lstMilk.add(p)
        if (p > 0)
            mapBuyMilk[ix + 1] = p

        lstCereal.add(ls[2].toInt())
    }

    val lstResteMilk = mutableListOf(0)
    lstResteMilk.add(lstMilk[1])
    var pMilk = lstMilk[1]

    val lstResteCereal = mutableListOf(0)
    lstResteCereal.add(lstCereal[1])
    var pCereal = lstCereal[1]

    for (day in 2 .. inLines.size) {
        var isMilk = false
        var isCereal = false

        pCereal += lstCereal[day]
        if (pCereal > 0) isCereal = true

        pMilk = lstResteMilk[day - 1]
        if (pMilk > 0) isMilk = true

        if (isMilk && isCereal) {
            pCereal -= 100
            pMilk -= 100
        }
        lstResteCereal.add(pCereal)

        if (mapBuyMilk.containsKey(day - 5)) {
            val achatMilk = mapBuyMilk[day - 5]!!
            pMilk -= achatMilk
            if (pMilk < 0) pMilk = 0
        }
        lstResteMilk.add(pMilk + lstMilk[day])
    }

    println(pMilk + pCereal)
}


