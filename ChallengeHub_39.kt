
fun main() {

    val inLines = readInput("input_39")
    //val inLines = readInput("test_39")

    val lstPlayers = mutableListOf<MutableList<Int>>()
    lstPlayers.add(mutableListOf())
    lstPlayers.add(mutableListOf())

    val mapPlayersScores = mutableMapOf<Int, Int>()
    mapPlayersScores[0] = 0
    mapPlayersScores[1] = 0

    val lstFlecheWin = mutableListOf<Int>()

    val lstPoints = mutableListOf(0)
    lstPoints.addAll(inLines[0].splitInts())

    var f = 0
    var inPartie = false
    var player = 1
    var firstPlayer = player
    var numPartie = -1

    while (f < lstPoints.size - 1) {

        if (!inPartie) {
            lstPlayers[0].add(0)
            lstPlayers[1].add(0)
            inPartie = true
            numPartie++
            if (firstPlayer == 0) {
                player = 1
                firstPlayer = 1
            }
            else {
                player = 0
                firstPlayer = 0
            }
        }

        for (j in 1 .. 3) {
            f++
            if (f >= lstPoints.size)
                break
            val points = lstPoints[f]
            lstPlayers[player][numPartie] += points
            if (lstPlayers[player][numPartie] == 501) {
                lstFlecheWin.add(points)
                mapPlayersScores[player] = mapPlayersScores[player]!!.plus(1)
                inPartie = false
                break
            }
        }

        if (inPartie) {
            if (player == 0)
                player = 1
            else
                player = 0
        }
    }

    println(lstFlecheWin.sum() * mapPlayersScores[0]!!)
}

// 10960536
