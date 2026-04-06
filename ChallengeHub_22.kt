
fun main() {

    val inLines = readInput("input_22")
    //val inLines = readInput("test_22")

    val mapLet = mutableMapOf<Char, Int>()
    mapLet['I'] = 9
    mapLet['V'] = 22
    mapLet['X'] = 24
    mapLet['L'] = 12
    mapLet['C'] = 3
    mapLet['D'] = 4
    mapLet['M'] = 13

    fun trt1(v:Int) : String {
        var sRom = ""
        if (v == 9)
            sRom = "IX"
        else if (v >= 5) {
            sRom = "V"
            val r = v - 5
            for (i in 1 .. r) {
                sRom += "I"
            }
        }
        else if (v == 4)
            sRom = "IV"
        else {
            for (i in 1..v) {
                sRom += "I"
            }
        }
        return sRom
    }

    fun trt10(v:Int) : String {
        var sRom = ""
        val v1 = v / 10
        val r1 = v % 10
        if (v1 == 9) {
            sRom = "XC"
        }
        else if (v1 >= 5) {
            val r1 = v1 - 5
            sRom = "L"
            for (i in 1 .. r1) {
                sRom += "X"
            }
        }
        else if (v1 == 4) {
            sRom = "XL"
        }
        else for (i in 1 .. v1) {
            sRom += "X"
        }

        if (r1 > 0)
            sRom += trt1(r1)

        return sRom
    }

    fun trt100(v:Int) : String {
        var sRom = ""
        val v1 = v / 100
        val r1 = v % 100
        if (v1 == 9)
            sRom = "CM"
        else if (v1 > 5) {
            val r2 = v1 - 5
            sRom += "D"
            for (i in 1 .. r2)
                sRom += "C"
        }
        else if (v1 == 5) {
            sRom += "D"
        }
        else if (v1 == 4) {
            sRom += "CD"
        }
        else for (i in 1 .. v1) {
            sRom += "C"
        }
        if (r1 > 0)
            sRom += trt10(r1)

        return sRom
    }

    fun trt1000(v:Int) : String {
        var sRom = ""
        val v1 = v / 1000
        val r1 = v % 1000
        for (i in 1 .. v1) {
            sRom += "M"
        }
        if (r1 >= 100) {
            sRom += trt100(r1)
        }
        else
            sRom += trt10(r1)

        return sRom
    }


    fun convRomain(v:Int) : String {
        var sRom = ""
        if (v >= 1000) {
            sRom = trt1000(v)
        }
        else if (v >= 100) {
            sRom = trt100(v)
        }
        else if (v >= 10) {
            sRom = trt10(v)
        }
        else {
            sRom = trt1(v)
        }

       return sRom
    }

    fun numRomain(v:String) : Int {
        var x = 0
        v.forEach { car ->
            x += mapLet[car]!!
        }
        return x
    }

    val lstIn = inLines[0].splitInts()

    var rep = 0
    lstIn.forEach {
        val vRom = convRomain(it)
        rep += numRomain(vRom)
    }

    println(rep)
}

// 43103
