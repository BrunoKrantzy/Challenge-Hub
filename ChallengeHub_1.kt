
fun main() {

    val inLines = readInput("input_1")
    //val inLines = readInput("test_1")

    /*
    Set the string's non-hexadecimal characters to 0.
    Pad the string length to the next multiple of 3.
    Split the result into 3 equal sections.
    The first two digits of each remaining section are the hex components.
    */

    var sOut= ""
    inLines[0].forEach {
        if (it in 'a'..'f' || it in '0'..'9')
            sOut += it
        else
            sOut += '0'
    }

    while (sOut.length % 3 != 0) {
        sOut += '_'
    }

    val size = sOut.length / 3
    val s1 = sOut.substring(0..size - 1)
    val s2 = sOut.substring(size..(size * 2)-1)
    val s3 = sOut.substring(size * 2)

    println(s1.substring(0,2) + s2.substring(0,2) + s3.substring(0,2))
}

