package com.muhammadraafi.projectsunshine.model

import com.google.gson.annotations.SerializedName

/**
 * Created by uty1 on 27/01/2018.
 */
data class ForecastResponse(
        @field:SerializedName("cod")
        val cod: String? = "",

        @field:SerializedName("message")
        val message: String? = "",
        val city: City? = City(),

        @field:SerializedName("list")
        val forecastList: List<Forecast>? = null
)

data class City(
        @field:SerializedName("id")
        val id: String? = "",

        @field:SerializedName("name")
        val name: String? = "",

        @field:SerializedName("country")
        val country: String? = ""

)

data class Forecast(
        @field:SerializedName("dt_txt")
        var dtText: String? = "",

        @field:SerializedName("weather")
        val weather: List<Weather>? = null,

        @field:SerializedName("main")
        val main: Temp? = null

)

data class Temp(
        @field:SerializedName("temp")
        val temp: String? = "",

        @field:SerializedName("temp_min")
        val temp_min: String? = ""
)

data class Weather(
        @field:SerializedName("id")
        val id: String? = "",

        @field:SerializedName("main")
        val main: String? ="",

        @field:SerializedName("description")
        val description: String? = "",

        @field:SerializedName("icon")
        val icon: String? = ""
)
