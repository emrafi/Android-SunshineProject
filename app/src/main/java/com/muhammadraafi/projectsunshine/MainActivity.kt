package com.muhammadraafi.projectsunshine

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.e
import android.util.Log.i
import com.google.gson.Gson
import com.muhammadraafi.projectsunshine.model.Forecast
import com.muhammadraafi.projectsunshine.model.ForecastResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val tag =this::class.java.simpleName

    private lateinit var adapter: ForecastAdapter
    private val forecastList = mutableListOf<Forecast>()

    //https://api.openweathermap.org/data/2.5/forecast?id=1621177&APPID=b3d9354eb85e9c679b81362ac0b9e730

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        getData()
    }

    private fun setupView(){
        adapter = ForecastAdapter(forecastList)
        daftarCuaca.layoutManager = LinearLayoutManager(this)
        daftarCuaca.adapter = adapter
    }

    private fun getData(){
        Application.api.getForcast().enqueue(object : Callback<ForecastResponse>{
            override fun onFailure(call : Call<ForecastResponse>?, t: Throwable){
                e(tag, t?.message)
            }
            override fun onResponse(call : Call<ForecastResponse>?, response: Response<ForecastResponse>?){
                i(tag, "data : ${Gson().toJsonTree(response?.body())}")

                val forecastResponse : ForecastResponse? =response?.body()

                val kota =forecastResponse?.city?.name
                val kodeNegara = forecastResponse?.city?.country?.toUpperCase()
                lokasi.text = "$kota, $kodeNegara"


                val nForecastList = forecastResponse?.forecastList
                nForecastList?.map {
                    it.dtText = convertToWeekDay(it.dtText)
                }

                val newList = nForecastList?.distinctBy {
                    it.dtText
                }

                    // menampilakn cur forecast ke home
                displayCurForecast(newList?.get(0))
                newList?. let {
                    forecastList.addAll(it)
                    forecastList.removeAt(0)
                    adapter.notifyDataSetChanged()
                }

            }
        })
    }

    @SuppressLint("setTextI18n")
    private fun displayCurForecast(forecast: Forecast?) {
        hariIni.text = "Hari ini"
        suhuSiang.text = forecast?.main?.temp?.toDouble()?.toInt()?.minus(273)?.toString()+"\u00B0"
        suhuMalam.text = forecast?.main?.temp_min?.toDouble()?.toInt()?.minus(273)?.toString()+"\u00B0"

        val icon = getIconWeater(forecast?.weather?.get(0)?.id?.toInt()?:0)
        gambarStatus.setImageDrawable(ContextCompat.getDrawable(this,icon))

        status.text = forecast?.weather?.get(0)?.description
    }
}
