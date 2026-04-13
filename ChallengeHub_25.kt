import java.math.BigInteger
import java.time.LocalTime

fun main() {

    val ficMorse = readInput("morse_25")
    val mapMorseCodeLet = mutableMapOf<String, Char>()

    ficMorse.forEach { line ->
        val let = line[0]
        val code = line.substringAfter("| ")
        mapMorseCodeLet[code] = let
    }

    val unitTime = 717.toBigInteger()

    val inLines = readInput("input_25")
    //val inLines = readInput("test_25")


    fun calculTime(timeIn:String) : BigInteger {
        val time = LocalTime.parse(timeIn)
        val tH = time.hour.toBigInteger() * 3600000.toBigInteger()
        val tM = time.minute.toBigInteger() * 60000.toBigInteger()
        val tS = time.second.toBigInteger() * 1000.toBigInteger()
        val timeOut = (tH + tM + tS + (time.nano / 1000000).toBigInteger())
        return timeOut
    }

    var newDay = true
    var trace = true
    val codeMorse = StringBuilder()
    var timePos = 0.toBigInteger()

    for (t in 0 until inLines.size-1) {
        val txt = inLines[t]
        if (txt.trim().isEmpty()) {
            codeMorse.append("$")
            newDay = true
            continue
        }
        else if (newDay) {
            timePos = calculTime(txt)
            newDay = false
            trace = true
        }
        else {
            val timeNext = calculTime(txt)
            val duree = ((timeNext - timePos) / unitTime).toInt()

            when (duree) {
                1 -> {
                    if (trace) {
                        codeMorse.append(".")
                        trace = false
                    }
                    else {
                        trace = true
                    }
                }
                3 -> {
                    if (trace) {
                        codeMorse.append("-")
                        trace = false
                    }
                    else {
                        codeMorse.append("|")
                        trace = true
                    }
                }
                7 -> {
                    codeMorse.append("%")
                    trace = true
                }
                else -> println("ERROR 1 : $duree")
            }
            timePos = timeNext
        }
    }
    codeMorse.append("%")

    val message = StringBuilder()
    var lettre = ""

    codeMorse.forEach { car ->
        when (car) {
            '$' -> {
                if (mapMorseCodeLet.containsKey(lettre)) {
                    message.append(mapMorseCodeLet[lettre])
                }
                else {
                    println("ERROR 2 : $lettre")
                }
                lettre = ""
                message.append("\n*****\n")
            }
            '%' -> {
                if (mapMorseCodeLet.containsKey(lettre)) {
                    message.append(mapMorseCodeLet[lettre])
                }
                else {
                    println("ERROR 2 : $lettre")
                }
                lettre = ""
                message.append(" ")
            }
            '|' -> {
                if (mapMorseCodeLet.containsKey(lettre)) {
                    message.append(mapMorseCodeLet[lettre])
                }
                else {
                    println("ERROR 2 : $lettre")
                }
                lettre = ""
            }
            else -> {
                lettre += car
            }
        }
    }

    println(message)
}
