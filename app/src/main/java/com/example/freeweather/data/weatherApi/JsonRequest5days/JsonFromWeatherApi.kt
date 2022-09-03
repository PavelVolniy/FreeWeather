package com.example.freeweather.data.weatherApi.JsonRequest5days

data class JsonFromWeatherApi(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: kotlin.collections.List<List>,
    val message: Int
)