package com.example.freeweather.data.weatherApi.JsonRequest5days

data class Main(
    val feels_like: Double,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Double,
    val temp_kf: Double,
    val temp_max: Double,
    val temp_min: Double
){
    fun getTempInt(): Int {
        return temp.toInt()
    }
}