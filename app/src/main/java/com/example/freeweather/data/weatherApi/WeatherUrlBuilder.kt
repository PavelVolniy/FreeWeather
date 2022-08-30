package com.example.freeweather.data.weatherApi.jsonWeatherApi

import java.lang.StringBuilder

class WeatherUrlBuilder(private val city: String,private val token: String) {
    private val BASE_URL = "https://pro.openweathermap.org/data/2.5/forecast/hourly?q="
    private val appId = "&appid="
    private var local: String? = null
    private var units: String? = null
    private val result = StringBuilder()

    fun setLocal(local: String): WeatherUrlBuilder {
        this.local = local
        return this
    }

    fun setUnits(units: String): WeatherUrlBuilder {
        this.units = units
        return this
    }


    fun build(): String{
        result.append(BASE_URL)
        if (city==null) throw RuntimeException("Not city name")
        else result.append(city)
        if (token==null) throw RuntimeException("Token is missing")
        else result.append(appId).append(token)
        if (local!=null) result.append(local)
        if (units!=null) result.append(units)
        return result.toString()
    }

    class WeatherUrl{
        private var city: String? = null
        private var token: String? = null
        private var local: String? = null
        private var units: String? = null

        private constructor(weatherUrlBuilder: WeatherUrlBuilder){
            this.city = weatherUrlBuilder.city
            this.local = weatherUrlBuilder.local
            this.token = weatherUrlBuilder.token
            this.units = weatherUrlBuilder.units
        }
    }


}