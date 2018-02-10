package com.muhammadraafi.projectsunshine

import com.muhammadraafi.projectsunshine.model.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by uty1 on 27/01/2018.
 */
interface Api {
  @GET("forecast?id=1621177&APPID=b3d9354eb85e9c679b81362ac0b9e730")
    fun getForcast(): Call<ForecastResponse>
}