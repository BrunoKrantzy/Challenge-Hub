
data class Tile(val num:Int) {
    var lx = 0
    var ly = 0
    var ux = 0
    var uy = 0
    var size = 0

    fun cSize() : Int {
        var sz = 0
        sz = (ux - lx) * (uy - ly)
        return sz
    }
}

fun main() {

    val inLines = readInput("input_11")
    //val inLines = readInput("test_11")

    var lstTiles = mutableListOf<Tile>()
    inLines.forEachIndexed { ix, string ->
        val (lx, ly, ux, uy) = string.splitInts()
        val tile = Tile(ix)
        tile.ux = ux
        tile.lx = lx
        tile.uy = uy
        tile.ly = ly
        tile.size = tile.cSize()
        lstTiles.add(tile)
    }

    val setTiles = mutableSetOf<Pair<Int,Int>>()
    val setUsedTiles = mutableSetOf<Pair<Int,Int>>()
    val mapTilesNum = mutableMapOf<Pair<Int,Int>, MutableSet<Int>>()

    lstTiles.forEach {
        val contenuTile = mutableListOf<Pair<Int,Int>>()
        for (x in it.lx until it.ux) {
            for (y in it.ly until it.uy) {
                val p = Pair(x, y)
                contenuTile.add(p)
                if (setTiles.contains(p))
                    setUsedTiles.add(p)
                else
                    setTiles.add(p)

                if (mapTilesNum.containsKey(p))
                    mapTilesNum[p]!!.add(it.num)
                else
                    mapTilesNum[p] = mutableSetOf(it.num)
            }
        }
    }

    val setDomConnected = mutableSetOf<Int>()
    mapTilesNum.forEach {
        if (it.value.size > 1) {
            it.value.forEach { num ->
                setDomConnected.add(num)
            }
        }
    }

    var rep = 0
    setDomConnected.forEach { num ->
        rep += lstTiles[num].size
    }
    rep -= setUsedTiles.size

    println(rep)
}

// 216
