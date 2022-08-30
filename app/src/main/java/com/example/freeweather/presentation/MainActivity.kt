package com.example.freeweather.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.freeweather.R
import com.example.freeweather.data.Token
import com.example.freeweather.data.retrofit2.RetrofitClient
import com.example.freeweather.data.weatherApi.JsonRequest5days.JsonResponse5DaysFromWeatherApi
import com.example.freeweather.data.weatherApi.RequestParam
import com.example.freeweather.data.weatherApi.WeatherApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val retrofit = RetrofitClient.getInstance()

        val weatherApiService: WeatherApiService = retrofit.create(WeatherApiService::class.java)

        weatherApiService.getWeatherData(
            "Зерноград",
            Token.getToken(),
            RequestParam.Local.localRU,
            RequestParam.Units.metric
        ).enqueue(object : Callback<JsonResponse5DaysFromWeatherApi>{
            override fun onResponse(
                call: Call<JsonResponse5DaysFromWeatherApi>,
                response: Response<JsonResponse5DaysFromWeatherApi>
            ) {
                val weather = response.body()
                weather?.let { Log.e("test---", it.toString()) }
                for (item in weather?.list!!){
                    Log.e("test --- ", item.toString())
                }
            }

            override fun onFailure(call: Call<JsonResponse5DaysFromWeatherApi>, t: Throwable) {
                Log.e("error============", t.message.toString())
            }
        })
    }

}



