package com.example.freeweather.data.weatherApi.JsonRequest5days

import kotlin.collections.List

data class List(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)