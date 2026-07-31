
data class Pic(var h:Int) {
    var numPic = 0
    var pro = 0
}

fun main() {

    val inLines = readInput("input_40")
    //val inLines = readInput("test_40")
    val lstH = inLines[0].splitInts()

    val lstPic = mutableListOf<Pic>()
    var nPic = -1
    var maxPic = 0

    var oldPh = lstH[0]
    var newPic = Pic(oldPh)
    newPic.numPic = nPic

    var inPositif = true
    for (i in 1 until lstH.size) {
        nPic++
        val v = lstH[i]
        maxPic = maxOf(maxPic, v)

        if (v < oldPh) {
            if (inPositif) {
                newPic.h = oldPh
                newPic.numPic = nPic
                oldPh = v
                inPositif = false
            }
            else {
                oldPh = v
            }
        }
        if (v > oldPh) {
            if (!inPositif) {
                lstPic.add(newPic)
                newPic = Pic(v)
                newPic.numPic = nPic
                oldPh = v
                inPositif = true
            }
            else {
                oldPh = v
            }
        }
    }
    lstPic.add(newPic)


    for (i in 0 until lstPic.size) {
        val h = lstPic[i].h

        if (h == maxPic) {
            lstPic[i].pro = maxPic
            continue
        }

        // prev
        var ii = lstPic[i].numPic - 1
        var proPrev = maxPic
        while (ii > -1) {
            val picPrev = lstH[ii]
            if (picPrev >= h) {
                break
            }
            proPrev = minOf(picPrev, proPrev)
            ii--
        }

        // next
        ii = lstPic[i].numPic + 1
        var proNext = maxPic
        while (ii <= nPic) {
            val picNext = lstH[ii]
            if (picNext >= h) {
                break
            }
            proNext = minOf(picNext, proNext)
            ii++
        }

        lstPic[i].pro = minOf(h - proPrev, h - proNext)
    }

    var rep = 0
    lstPic.forEach {
        rep += it.pro
    }

    println(rep)
}

// 6750
