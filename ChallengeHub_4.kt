
fun main() {

    val inLines = readInput("input_4")
    //val inLines = readInput("test_4")

    var num = inLines[0].toLong()

    var lstCoPrimes = mutableListOf(1L)

    val lstFacNum = mutableListOf<Long>()
    for (n in num - 1 downTo 2) {
        if (num % n == 0L) {
            lstFacNum.add(n)
        }
    }

    for (n in num-1 downTo 2) {
        var tem = true
        for (item in lstFacNum) {
            if (n % item == 0L) {
                tem = false
                break
            }
        }
        if (tem)
            lstCoPrimes.add(n)
    }

    println(lstCoPrimes.sum())
}
