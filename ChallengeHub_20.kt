
fun main() {

    val inLines = readInput("input_20")
    //val inLines = readInput("test_20")

    val lstCards = inLines[0].split(" ").toMutableList()
    var aces = 0
    var wins = 0
    var som = 0

    fun casino() : Int {
        for (p in 0 until lstCards.size) {
            val card = lstCards[p]
            when (card) {
                "A" -> {
                    aces++
                    som += 1
                }
                "J","Q","K","10" -> {
                    som += 10
                }
                else -> {
                    val v = card.toInt()
                    som += v
                }
            }

            if (som > 21) {
                som = 0
                aces = 0
                continue
            }

            for (i in 0 .. aces) {
                val v = som + (i * 10)
                if (v == 21) {
                    wins++
                    som = 0
                    aces = 0
                }
            }
        }

        return wins
    }

    wins = casino()
    println(wins)
}
