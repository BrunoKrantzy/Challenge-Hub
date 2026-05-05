
fun main() {

    val inLines = readInput("input_35")
    //val inLines = readInput("test_35")

    val txtIn = inLines[0].dropLast(1)

    val lstWords = readInput("lstwords_35").toHashSet()
    val mapCodeWord = mutableMapOf<String, String>()

    lstWords.forEach { word ->
        val lstW = word.toMutableList()
        lstW.sort()
        var code = ""
        val codeSet = mutableSetOf<Int>()
        word.forEach { car ->
            var pos = lstW.indexOf(car)
            while (codeSet.contains(pos))
                pos++
            codeSet.add(pos)
            code += pos.toString()
        }
        mapCodeWord[code] = word
    }

    fun decoupeTexte(texte: String, lg: Int): MutableList<String> {
        val lstLignes = mutableListOf<String>()
        val nbLignes = texte.length / lg
        var pos = 0
        for (i in 1..nbLignes) {
            val rng = pos..(pos + lg) - 1
            val ligne = texte.slice(rng)
            lstLignes.add(ligne)
            pos += lg
        }
        return lstLignes
    }

    mapCodeWord.forEach { code, _ ->
        val lg = code.length
        var lstLignes: MutableList<String>
        if (txtIn.length % lg == 0) {
            val rng = txtIn.length / lg
            lstLignes = decoupeTexte(txtIn, rng)
            val nbLigTab = lstLignes[0].length
            val tabTexte = Array(nbLigTab) { Array(lg) { '.' } }

            for (col in 0 until lg) {
                val p = code[col].code - 48
                val ligne = lstLignes[p]
                for (c in 0 until ligne.length) {
                    tabTexte[c][col] = ligne[c]
                }
            }

            val output = StringBuilder()
            output.append("CODE : ${mapCodeWord[code]}\n")
            for (lig in 0 until tabTexte.size) {
                for (col in 0 until lg) {
                    output.append(tabTexte[lig][col])
                }
            }
            output.append("\n\n")

            var cp = 0
            val lst = output.split(" ")
            for (item in lst) {
                if (lstWords.contains(item)) {
                    cp++
                }
                if (cp == 500) {
                    println(output)
                    break
                }
            }
        }
    }
}
