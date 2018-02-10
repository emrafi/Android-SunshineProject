package com.muhammadraafi.projectsunshine

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by uty1 on 27/01/2018.
 */

class Application : Application(){

    companion object {
        lateinit var api: Api
    }

    override fun onCreate(){
        super.onCreate()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(Api::class.java)

    }
}