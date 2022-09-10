package com.example.freeweather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import com.example.freeweather.data.retrofit2.RetrofitClient
import com.example.freeweather.data.weatherApi.JsonRequest5days.JsonFromWeatherApi
import com.example.freeweather.data.weatherApi.RequestParam
import com.example.freeweather.data.weatherApi.Token
import com.example.freeweather.data.weatherApi.WeatherApiService
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val jsonResponse5DaysFromWeatherApi: MutableLiveData<JsonFromWeatherApi> =
        MutableLiveData()

    fun getJsonResultObserver(): MutableLiveData<JsonFromWeatherApi> {
        return jsonResponse5DaysFromWeatherApi
    }

    fun makeRequest(query: String) {
        val requestRetrofit = RetrofitClient.getInstance().create(WeatherApiService::class.java)
        requestRetrofit.getWeatherData(
            query,
            Token.getToken(),
            RequestParam.Local.localRU,
            RequestParam.Units.metric
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getWeatherObserverRx())
    }

    private fun getWeatherObserverRx(): Observer<JsonFromWeatherApi> {
        return object : Observer<JsonFromWeatherApi>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: JsonFromWeatherApi) {
                jsonResponse5DaysFromWeatherApi.postValue(t)
            }

            override fun onError(e: Throwable) {
                jsonResponse5DaysFromWeatherApi.postValue(null)
            }

            override fun onComplete() {

            }

        }
    }
}