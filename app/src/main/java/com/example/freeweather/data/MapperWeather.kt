package com.example.freeweather.data

import com.example.freeweather.R

object MapperWeather {
    fun getIdResources(descriptions: String): Int {
        when (descriptions) {
            "небольшой дождь" -> return R.drawable.ic_small_rain
            "дождь" -> return R.drawable.ic_rain
            "сильный дождь" -> return R.drawable.ic_rain
            "пасмурно" -> return R.drawable.ic_cloudy
            "переменная облачность" -> return R.drawable.ic_partly_cloudy
            "облачно с прояснениями" -> return R.drawable.ic_partly_cloudy
            "небольшая облачность" -> return R.drawable.ic_partly_cloudy
            "ясно" -> return R.drawable.ic_sunny
            "снег" -> return R.drawable.ic_snow
            "небольшой снег" -> return R.drawable.ic_light_snow
        }
        return R.drawable.ic_baseline_cloud_off_24_3
    }
}