
fun main() {

    val inLines = readInput("input_37")
    //val inLines = readInput("test_37")

    val lstAll = readInput("lstwords")
    val lstW5 = mutableListOf<String>()
    lstAll.forEach  {
        if (it.length == 5) {
            lstW5.add(it)
        }
    }
    val lstWords = lstW5.toHashSet()

    var rep = 0
    var trameMot = mutableListOf('_','_','_','_','_')
    val mapAbandon = mutableMapOf<Int, MutableSet<Char>>()
    val mapZeros = mutableMapOf<Int, MutableSet<Char>>()
    val mapPossibles = mutableMapOf<Int, MutableSet<Char>>()
    val setPossibles = mutableSetOf<Char>()
    val setZeros = mutableSetOf<Char>()
    val dicoVu = mutableSetOf<String>()
    var nbLetInTrame = 0
    var newWord = ""

    for (n in 1 until inLines.size) {
        val word = inLines[n].substringBefore(",")
        val lstCodes = inLines[n].substringAfter(",").splitInts()
        var isFound = false
        dicoVu.add(word)

        for (p in 0 .. 4) {
            val letter = word[p]
            val code = lstCodes[p]

            when (code) {
                0 -> {
                    if (setPossibles.contains(letter)) {
                        if (mapAbandon.containsKey(p)) {
                            mapAbandon[p]!!.add(letter)
                        }
                        else {
                            mapAbandon[p] = mutableSetOf(letter)
                        }
                    }
                    else {
                        for (x in 0 .. 4) {
                            if (mapAbandon.containsKey(x)) {
                                mapAbandon[x]!!.add(letter)
                            }
                            else {
                                mapAbandon[x] = mutableSetOf(letter)
                            }
                        }
                    }
                    setZeros.add(letter)
                    if (mapZeros.containsKey(p)) {
                        mapZeros[p]!!.add(letter)
                    }
                    else {
                        mapZeros[p] = mutableSetOf(letter)
                    }

                    if (trameMot.contains(letter)) {
                        val px = trameMot.indexOf(letter)
                        mapAbandon[px]!!.remove(letter)
                    }
                }
                1 -> {
                    if (mapPossibles.containsKey(p)) {
                        mapPossibles[p]!!.add(letter)
                    }
                    else {
                        mapPossibles[p] = mutableSetOf()
                        mapPossibles[p]!!.add(letter)
                    }
                    setPossibles.add(letter)
                }
                2 -> {
                    if (trameMot[p] == '_') {
                        trameMot[p] = letter
                        nbLetInTrame++
                    }
                    else if (trameMot[p] != letter)
                        println("ERREUR on a été trop loin : $n - $word")

                    if (mapAbandon.containsKey(p)) {
                        if (mapAbandon[p]!!.contains(letter)) {
                            mapAbandon[p]!!.remove(letter)
                        }
                    }
                }
            }
        }

        word.forEach { l ->
            if (setZeros.contains(l) && setPossibles.contains(l) && trameMot .contains(l)) {
                for (i in 0 .. 4) {
                    if (mapZeros.containsKey(i)) {
                        if (mapZeros[i]!!.contains(l)) {
                            mapAbandon[i]!!.add(l)
                        }
                        if (trameMot[i] != l)
                            mapAbandon[i]!!.add(l)
                    }
                    else if (trameMot[i] != l)
                            mapAbandon[i]!!.add(l)
                    else
                        mapAbandon[i]!!.add(l)
                }
            }
        }

        val mapConnues = mutableMapOf<Int, Char>()
        trameMot.forEachIndexed { ix, l ->
            if (l != '_')
                mapConnues[ix] = l
        }

        val lstPossibles = mutableSetOf<String>()
        for (w in lstWords) {
            var temoin = true

            for (it in mapConnues) {
                if (w[it.key] != it.value) {
                    temoin = false
                    break
                }
            }

            for (it in mapPossibles) {
                val lstChars = mapPossibles[it.key]
                lstChars!!.forEach { car ->
                    if (!w.contains(car)) {
                        temoin = false
                        break
                    }
                    else if (w[it.key] == car) {
                        temoin = false
                        break
                    }
                }
            }

            for (it in mapAbandon) {
                val lstChars = mapAbandon[it.key]
                lstChars!!.forEach { car ->
                    if (w[it.key] == car) {
                        temoin = false
                        break
                    }
                }
            }

            if (temoin && !dicoVu.contains(w))
                lstPossibles.add(w)
        }

        if (lstPossibles.size == 1) {
            newWord = lstPossibles.first()
            if (lstWords.contains(newWord))
                isFound = true
            else
                println("ERREUR pas dans le dico : $newWord")
        }

        if (isFound) {
            println("$word -> $newWord")
            newWord.forEach { l ->
                rep += l.code - 97
            }
            trameMot = mutableListOf('_','_','_','_','_')
            newWord = ""
            mapAbandon.clear()
            mapZeros.clear()
            mapPossibles.clear()
            setPossibles.clear()
            setZeros.clear()
            dicoVu.clear()
            nbLetInTrame = 0
        }
    }

    println(rep)
}

// == 4150
