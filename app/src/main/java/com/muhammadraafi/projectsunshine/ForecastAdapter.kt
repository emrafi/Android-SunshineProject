package com.muhammadraafi.projectsunshine

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.muhammadraafi.projectsunshine.model.Forecast
import com.muhammadraafi.projectsunshine.model.Weather
import kotlinx.android.synthetic.main.daftar_forecast.view.*

/**
 * Created by uty1 on 27/01/2018.
 */

class ForecastAdapter(private val forecastList : List<Forecast>) : RecyclerView.Adapter<ForecastAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view: View? = LayoutInflater.from(parent?.context).inflate(R.layout.daftar_forecast, parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = forecastList.size

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bind(position)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {

            val forecast : Forecast = forecastList[position]
            i("adapter","forecast" + Gson().toJsonTree(forecast))

            if(forecast?.weather?.isNotEmpty() == true){
                val weather : Weather = forecast.weather[0]
                itemView?. listWaktu?.text = forecast.dtText
                val degreeInCel = forecast.main?.temp?.toDouble()?.minus(273)
                itemView?. listSuhuSiang?.text = "${degreeInCel?.toInt()}\u00B0"
                val degreeInCel2 = forecast.main?.temp_min?.toDouble()?.minus(273)
                itemView?. listSuhuMalam?.text = "${degreeInCel2?.toInt()}\u00B0"
                itemView?. listCuaca?.text= weather.description
                val icon : Int = getIconWeater(weather.id?.toInt()?:0)
                itemView?. listGambarStatus?.setImageDrawable(ContextCompat.getDrawable(itemView.context,icon))
            }
        }

    }
}