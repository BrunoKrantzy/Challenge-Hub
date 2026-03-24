
data class Letter(val let: Char) {
    var largeur = 0
    var matrice = Array(0) { IntArray(largeur) { -1 } }
    var nbSpaces = 0
}

fun main() {
    val mapLetters = mutableMapOf<String, Letter>()
    val mapLiaisons = mutableMapOf<String, Pair<Int,Int>>()
    for (let1 in 'A' .. 'Z') {
        val l1 = let1.toString()
        for (let2 in 'A' .. 'Z') {
            val l2 = let2.toString()
            val liaison = (l1 + l2)
            mapLiaisons[liaison] = Pair(0, 0)
        }
    }

    val inLines = readInput("input_16")
    //val inLines = readInput("test_16")

    val alphabet = readInput("alphabet")
    val nbLines = alphabet.size

    var pos = -1
    var let= 'A'
    val lstLines = mutableListOf<String>()
    while (pos < nbLines-1) {
        var len = 0
        for (i in 1 .. 6) {
            pos++
            val ligne = alphabet[pos]
            len = maxOf(len, ligne.length)
            lstLines.add(ligne)
        }
        val cL = Letter(let)
        cL.largeur = len
        cL.matrice = Array(6) { IntArray(len) }

        var sp = 0
        lstLines.forEachIndexed { ix, ligne ->
            ligne.forEachIndexed { jx, let ->
                if (let == ' ') {
                    sp++
                    cL.matrice[ix][jx] = 0
                }
                else
                    cL.matrice[ix][jx] = 1
            }
        }
        cL.nbSpaces = sp + 6 // colonne de séparation
        mapLetters[let.toString()] = cL

        lstLines.clear()
        let += 1
    }

    fun deepCopyOf(matrice: Array<IntArray>) : Array<IntArray> {
        val v1 = matrice.size
        val v2 =matrice[0].size
        val newMatrice = Array(v1) { IntArray(v2) }
        for (l in 0 until v1) {
            for (c in 0 until v2) {
                newMatrice[l][c] = matrice[l][c]
            }
        }
        return newMatrice
    }

    var deducKeep = 0
    fun comptageSpaces(cL1:Letter, cL2:Letter) : Pair<Int, Int> {
        var nbEspaces = 0
        var deducPart = 0
        val l1 = cL1.matrice
        val l2 = cL2.matrice
        val lMax = 1 + cL1.largeur + cL2.largeur
        val matriceLiaison = Array(6) { IntArray(lMax) { -1 } }
        val lstMatricesLiaison = mutableListOf<Array<IntArray>>()

        for (l in 0 .. 5) {
            for (c1 in 0 until cL1.largeur) {
                matriceLiaison[l][c1] = l1[l][c1]
            }
            // séparateur :
            matriceLiaison[l][cL1.largeur] = 0
            for (c2 in 0 until cL2.largeur) {
                var c = l2[l][c2]
                if (c > 0) c = 2
                matriceLiaison[l][1 + cL1.largeur + c2] = c
            }
        }
        val copyMatrice = deepCopyOf(matriceLiaison)
        lstMatricesLiaison.add(copyMatrice)

        // test de collision :
        var collision = false
        var posDep = cL1.largeur
        while (!collision) {
            for (lig in 0 .. 5) {
                if (collision) {
                    break
                }
                for (col in 0 until matriceLiaison[lig].size - 2) {
                    if (matriceLiaison[lig][col] == 1 && matriceLiaison[lig][col + 1] == 0 && matriceLiaison[lig][col + 2] == 2) {
                        collision = true
                        break
                    }
                }
            }

            for (col in posDep .. cL1.largeur + cL2.largeur) {
                if (collision) {
                    break
                }
                for (lig in 0 .. 5) {
                    val v1 = matriceLiaison[lig][col-1]
                    val v2 = matriceLiaison[lig][col]
                    if (v2 == 2 && v1 == 0) {
                        matriceLiaison[lig][col-1] = v1 + v2
                        matriceLiaison[lig][col] = 0
                    }
                }
            }
            val newMatrice = deepCopyOf(matriceLiaison)
            if (!collision) {
                lstMatricesLiaison.add(newMatrice)
            }
            posDep--
        }

        // comptage des espaces :
        val liaison = lstMatricesLiaison.last()
        // col du premier 2 dans la liaison
        var premCol = 999
        val lg = liaison[0].size
        for (lig in 0 .. 5) {
            for (col in 0 until lg) {
                if (liaison[lig][col] == 2) {
                    premCol = minOf(premCol,col)
                }
            }
        }

        // nb spaces dans l'espace letter 1
        for (lig in 0 .. 5) {
            for (col in 0 .. cL1.largeur) {
                if (liaison[lig][col] == 0) {
                    nbEspaces++
                }
            }
        }

        // nb spaces de la part à déduire du car suivant
        for (lig in 0 .. 5) {
            for (col in premCol .. cL1.largeur) {
                if (liaison[lig][col] == 0 || liaison[lig][col] == 1) {
                    deducPart++
                }
            }
        }

        return Pair(nbEspaces, deducPart)
    }

    mapLiaisons.forEach { liaison, _ ->
        val l1 = liaison[0]
        val l2 = liaison[1]
        val cL1 = mapLetters[l1.toString()]!!
        val cL2 = mapLetters[l2.toString()]!!

        val deduc = comptageSpaces(cL1, cL2)
        mapLiaisons[liaison] = deduc
    }

    var totalSpaces = 0
    for (car in 0 until inLines[0].length - 1) {
        val l1 = inLines[0][car].toString()
        val l2 = inLines[0][car+1].toString()
        val liaison = l1 + l2
        val nbSpLiaison = mapLiaisons[liaison]!!.first

        totalSpaces += nbSpLiaison - deducKeep
        deducKeep = mapLiaisons[liaison]!!.second
    }

    // dernière lettre :
    val l = inLines[0].last().toString()
    val derLet = mapLetters[l]
    totalSpaces += derLet!!.nbSpaces - deducKeep

    println(totalSpaces - 6)
}

// 246882
