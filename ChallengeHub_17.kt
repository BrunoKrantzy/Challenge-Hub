import java.time.LocalDate
import java.time.Period

fun main() {

    val inLines = readInput("input_17")
    //val inLines = readInput("test_17")

    val mapInZero = mutableMapOf<String, Boolean>()
    val mapDaysInZero = mutableMapOf<String, Double>()
    val mapDatesEnCours = mutableMapOf<String, Pair<String, String>>()
    val mapLongestDates = mutableMapOf<String, Pair<LocalDate, LocalDate>>()

    //date,home_team,away_team,home_score,away_score,tournament,city,country,neutral
    //1890-02-08,Wales,Northern Ireland,5,2,British Championship,Shrewsbury,England,TRUE

    inLines.forEach {
        val elements = it.split(",")
        val dateMatch = elements[0]
        val p1 = elements[1]
        val p2 = elements[2]
        val score1 = elements[3].toInt()
        val score2 = elements[4].toInt()

        if (score1 == 0) {
            if (mapInZero.containsKey(p1)) {
                if (mapInZero[p1] == false) {
                    mapInZero[p1] = true
                    mapDatesEnCours[p1] = Pair(dateMatch, "0")
                }
            }
            else {
                mapInZero[p1] = true
                mapDatesEnCours[p1] = Pair(dateMatch, "0")
            }
        }
        else if (score1 > 0) {
            if (mapInZero.containsKey(p1)) {
                if (mapInZero[p1] == true) {
                    mapInZero[p1] = false
                    val d1 = mapDatesEnCours[p1]!!.first
                    val start = LocalDate.parse(d1)
                    val d2 = dateMatch
                    val end = LocalDate.parse(d2)

                    val period = Period.between(start, end)
                    val nbDays1 = period.months * (365.2425 / 12)
                    val nbDays2 = period.years * 365.2425
                    val nbDays = period.days + nbDays1 + nbDays2

                    if (mapDaysInZero.containsKey(p1)) {
                        val v1 = mapDaysInZero[p1]!!
                        if (nbDays > v1) {
                            mapDaysInZero[p1] = nbDays
                            mapLongestDates[p1] = Pair(start, end)
                        }
                    }
                    else {
                        mapDaysInZero[p1] = nbDays
                        mapLongestDates[p1] = Pair(start, end)
                    }
                }
            }
        }


        if (score2 == 0) {
            if (mapInZero.containsKey(p2)) {
                if (mapInZero[p2] == false) {
                    mapInZero[p2] = true
                    mapDatesEnCours[p2] = Pair(dateMatch, "0")
                }
            }
            else {
                mapInZero[p2] = true
                mapDatesEnCours[p2] = Pair(dateMatch, "0")
            }
        }
        else if (score2 > 0) {
            if (mapInZero.containsKey(p2)) {
                if (mapInZero[p2] == true) {
                    mapInZero[p2] = false
                    val d1 = mapDatesEnCours[p2]!!.first
                    val start = LocalDate.parse(d1)
                    val d2 = dateMatch
                    val end = LocalDate.parse(d2)

                    val period = Period.between(start, end)
                    val nbDays1 = period.months * (365.2425 / 12)
                    val nbDays2 = period.years * 365.2425
                    val nbDays = period.days + nbDays1 + nbDays2

                    if (mapDaysInZero.containsKey(p2)) {
                        val v1 = mapDaysInZero[p2]!!
                        if (nbDays > v1) {
                            mapDaysInZero[p2] = nbDays
                            mapLongestDates[p2] = Pair(start, end)
                        }
                    }
                    else {
                        mapDaysInZero[p2] = nbDays
                        mapLongestDates[p2] = Pair(start, end)
                    }
                }
            }
        }
    }

    val maxDays = mapDaysInZero.maxOf { it.value }
    val pays = mapDaysInZero.filterValues { it == maxDays }.keys.first()
    val dates = mapLongestDates[pays]!!

    val startDate = dates.first.toString().replace("-","")
    val endDate = dates.second.toString().replace("-","")

    println("$pays $startDate $endDate")
}


