package com.example.freeweather.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.freeweather.R
import com.example.freeweather.data.retrofit2.RetrofitClient
import com.example.freeweather.data.weatherApi.JsonRequest5days.JsonFromWeatherApi
import com.example.freeweather.data.weatherApi.RequestParam
import com.example.freeweather.data.weatherApi.Token
import com.example.freeweather.data.weatherApi.WeatherApiService
import com.example.freeweather.domain.JsonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MAinViewModel
    lateinit var jsonAdapter: JsonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()


    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_RecyclerView)
        recyclerView.apply {
            layoutManager =LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
            jsonAdapter = JsonAdapter()
            adapter = jsonAdapter
        }
    }

}



