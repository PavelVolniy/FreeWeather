package com.example.freeweather.data.weatherApi

import com.example.freeweather.data.weatherApi.JsonRequest5days.JsonResponse5DaysFromWeatherApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("forecast")
    fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") token: String,
        @Query("lang") local: String,
        @Query("units") units: String
    ): Call<JsonResponse5DaysFromWeatherApi>
}