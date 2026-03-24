
fun main() {

    val inLines = readInput("input_3")
    //val inLines = readInput("test_3")

    val setGood = mutableSetOf<Pair<Int,Int>>()
    setGood.add(Pair(0,2))
    setGood.add(Pair(0,3))
    setGood.add(Pair(1,1))
    setGood.add(Pair(1,2))
    setGood.add(Pair(1,3))
    setGood.add(Pair(1,4))
    setGood.add(Pair(2,0))
    setGood.add(Pair(2,1))
    setGood.add(Pair(2,2))
    setGood.add(Pair(2,3))
    setGood.add(Pair(2,4))
    setGood.add(Pair(2,5))
    setGood.add(Pair(3,0))
    setGood.add(Pair(3,1))
    setGood.add(Pair(3,2))
    setGood.add(Pair(3,3))
    setGood.add(Pair(3,4))
    setGood.add(Pair(3,5))
    setGood.add(Pair(4,1))
    setGood.add(Pair(4,2))
    setGood.add(Pair(4,3))
    setGood.add(Pair(4,4))
    setGood.add(Pair(5,2))
    setGood.add(Pair(5,3))

    var pos = Pair(0,2)
    var lstPos = mutableListOf<Pair<Int,Int>>()

    inLines[0].forEach {
        when (it) {
            'U' -> {
                val p1 = pos.first - 1
                val newPos = Pair(p1,pos.second)
                if (setGood.contains(newPos)) {
                    pos = newPos
                }
                lstPos.add(pos)
            }
            'D' -> {
                val p1 = pos.first + 1
                val newPos = Pair(p1,pos.second)
                if (setGood.contains(newPos)) {
                    pos = newPos
                }
                lstPos.add(pos)
            }
            'L' -> {
                val p2 = pos.second - 1
                val newPos = Pair(pos.first,p2)
                if (setGood.contains(newPos)) {
                    pos = newPos
                }
                lstPos.add(pos)
            }
            'R' -> {
                val p2 = pos.second + 1
                val newPos = Pair(pos.first,p2)
                if (setGood.contains(newPos)) {
                    pos = newPos
                }
                lstPos.add(pos)
            }
        }
    }

    var rep = 0
    lstPos.forEach {
        rep += it.first + it.second
    }

    println(rep)
}
