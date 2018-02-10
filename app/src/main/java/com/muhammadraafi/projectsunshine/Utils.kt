package com.muhammadraafi.projectsunshine

import com.muhammadraafi.projectsunshine.model.Weather
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by uty1 on 28/01/2018.
 */

fun convertToWeekDay(date : String?) : String? {

    //konversi tipe tgl dr string ke date
    val weekdayName = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val weekdayDate : Date = weekdayName.parse(date)

    //konversi dari date ke hari
    val dayName = SimpleDateFormat("EEEE", Locale("id"))
    val day = dayName.format(weekdayDate)
    println(day)
    return day
}

fun getIconWeater(weatherId: Int):Int {

    return when (weatherId){
        in 200..232 -> R.drawable.ic_storm
        in 300..321 -> R.drawable.ic_light_rain
        in 500..504 -> R.drawable.ic_rain
        511         -> R.drawable.ic_snow
        in 520..531 -> R.drawable.ic_rain
        in 600..622 -> R.drawable.ic_snow
        in 701..761 -> R.drawable.ic_fog
        761,771,781 -> R.drawable.ic_storm
        800         -> R.drawable.ic_clear
        801         -> R.drawable.ic_light_clouds
        in 802..804 -> R.drawable.ic_cloudy
        in 900..906 -> R.drawable.ic_storm
        in 958..962 -> R.drawable.ic_storm
        in 951..957 -> R.drawable.ic_clear
        else        -> R.drawable.ic_storm

    }

}

