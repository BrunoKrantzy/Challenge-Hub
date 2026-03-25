fun main() {

    val inLines = readInput("input_19")
    //val inLines = readInput("test_19")

    fun runGame(runs:Int, dim:Int, cellsIn: MutableList<Int>) : Int {
        var grid = Array(dim+2) { CharArray(dim+2) { '.' } }
        var newGrid = Array(dim+2) { CharArray(dim+2) { '.' } }
        for (i in 0 until cellsIn.size - 1 step 2) {
            val lig = cellsIn[i]
            val col = cellsIn[i+1]
            grid[lig+1][col+1] = '#'
        }

        repeat (runs) {
            for (l in 1 .. dim) {
                for (c in 1 .. dim) {
                    var nbOn = 0
                    if (grid[l-1][c] == '#') nbOn++
                    if (grid[l][c+1] == '#') nbOn++
                    if (grid[l+1][c] == '#') nbOn++
                    if (grid[l][c-1] == '#') nbOn++

                    if (nbOn % 2 == 0)
                        newGrid[l][c] = '.'
                    else
                        newGrid[l][c] = '#'
                }
            }
            // swap des grids
            grid = newGrid
            newGrid = Array(dim+2) { CharArray(dim+2) { '.' } }
        }

        // comptage des cells ON
        var nbCellsOn = 0
        for (l in 1 .. dim) {
            for (c in 1 .. dim) {
                if (grid[l][c] == '#')
                    nbCellsOn++
            }
        }
        return nbCellsOn
    }


    var result = 0L
    inLines.forEach {
        val lstIn = it.splitInts()
        val nbRuns = lstIn[0]
        val dimGrid = lstIn[1]
        val lstCellsIn = mutableListOf<Int>()
        for (i in 2 until lstIn.size) {
            lstCellsIn.add(lstIn[i])
        }
        result += runGame(nbRuns, dimGrid, lstCellsIn)
    }

    println("Solution : $result")
}
