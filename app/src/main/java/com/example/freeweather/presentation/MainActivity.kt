package com.example.freeweather.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freeweather.R
import com.example.freeweather.adapters.JsonAdapter
import com.example.freeweather.adapters.JsonAdapterColumn
import com.example.freeweather.adapters.JsonAdapterRow
import com.example.freeweather.data.weatherApi.JsonRequest5days.List

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var jsonAdapter: JsonAdapter
    lateinit var jsonAdapterRow: JsonAdapterRow
    lateinit var jsonAdapterColumn: JsonAdapterColumn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadDate("Zernograd")


    }

    private fun loadDate(city: String) {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getJsonResultObserver().observe(this, Observer {
            if (it != null) {
                jsonAdapterColumn.list = it.list as java.util.ArrayList<List>
                jsonAdapterRow.list = it.list
                jsonAdapterRow.notifyDataSetChanged()
                jsonAdapterColumn.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Not found city name", Toast.LENGTH_LONG).show()
            }

        })
        viewModel.makeRequest(city)
    }

    private fun initRecyclerView() {
        val recyclerViewColumn = findViewById<RecyclerView>(R.id.rv_data_forecast_temp_list)
        recyclerViewColumn.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            jsonAdapterColumn = JsonAdapterColumn()
            adapter = jsonAdapterColumn
        }
        val recyclerViewRow = findViewById<RecyclerView>(R.id.rv_data_forecast)
        recyclerViewRow.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            jsonAdapterRow = JsonAdapterRow()
            adapter = jsonAdapterRow
        }
    }

}



