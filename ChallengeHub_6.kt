
fun main() {

    //val inLines = readInput("input_6")
    //val inLines = readInput("test_6")

    var base = 123
    var v1 = base
    var nb1 = 0

    var setResults = mutableSetOf<String>()

    fun seqValeurs(v1:Int, v2:Int, v3:Int) {
        val sva = v1.toString() + " " + v2.toString() + " " + v3.toString()
        setResults.add(sva)

        val svb = v1.toString() + " " + v3.toString() + " " + v2.toString()
        setResults.add(svb)

        val svc = v2.toString() + " " + v1.toString() + " " + v3.toString()
        setResults.add(svc)

        val svd = v2.toString() + " " + v3.toString() + " " + v1.toString()
        setResults.add(svd)

        val sve = v3.toString() + " " + v1.toString() + " " + v2.toString()
        setResults.add(sve)

        val svf = v3.toString() + " " + v2.toString() + " " + v1.toString()
        setResults.add(svf)
    }

    fun genListe(v1:Int) {
        val a = v1
        var b = 0
        var c = 0
        val dif = base - v1
        for (i in 0..dif/2) {
            b = i
            c = dif - i
            if (a + b + c == base)
                seqValeurs(a, b, c)
        }
    }

    while (v1 >= 0) {
        genListe(v1)
        v1--
    }

    setResults.forEach {
        val v = it.filter { c -> c == '1' }.count()
        nb1 += v
    }

    println(nb1)
}
