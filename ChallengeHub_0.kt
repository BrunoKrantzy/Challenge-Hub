
fun main() {

    val inLines = readInput("input_0")
    //val inLines = readInput("test_0")

    var lstNums = mutableListOf<MutableMap<Int, Char>>()

    val m0 = mutableMapOf<Int, Char>()
    m0[1] = ' '
    lstNums.add(m0)

    val m1 = mutableMapOf<Int, Char>()
    m1[1] = 'a'
    m1[2] = 'b'
    m1[3] = 'c'
    lstNums.add(m1)

    val m2 = mutableMapOf<Int, Char>()
    m2[1] = 'd'
    m2[2] = 'e'
    m2[3] = 'f'
    lstNums.add(m2)

    val m3 = mutableMapOf<Int, Char>()
    m3[1] = 'g'
    m3[2] = 'h'
    m3[3] = 'i'
    lstNums.add(m3)

    val m4 = mutableMapOf<Int, Char>()
    m4[1] = 'j'
    m4[2] = 'k'
    m4[3] = 'l'
    lstNums.add(m4)

    val m5 = mutableMapOf<Int, Char>()
    m5[1] = 'm'
    m5[2] = 'n'
    m5[3] = 'o'
    lstNums.add(m5)

    val m6 = mutableMapOf<Int, Char>()
    m6[1] = 'p'
    m6[2] = 'q'
    m6[3] = 'r'
    m6[4] = 's'
    lstNums.add(m6)

    val m7 = mutableMapOf<Int, Char>()
    m7[1] = 't'
    m7[2] = 'u'
    m7[3] = 'v'
    lstNums.add(m7)

    val m8 = mutableMapOf<Int, Char>()
    m8[1] = 'w'
    m8[2] = 'x'
    m8[3] = 'y'
    m8[4] = 'z'
    lstNums.add(m8)

    var rep = ""
    inLines.forEach {
        val ln = it.splitInts()
        var num = ln[0]
        if (num == 0)
            rep += " "
        else {
            num--
            val k = ln[1]
            rep += lstNums[num][k]
        }
    }

    println(rep)
}

