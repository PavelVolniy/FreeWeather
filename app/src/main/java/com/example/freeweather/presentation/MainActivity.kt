package com.example.freeweather.presentation

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freeweather.R
import com.example.freeweather.adapters.JsonAdapterColumn
import com.example.freeweather.adapters.JsonAdapterRow
import com.example.freeweather.data.MapperWeather
import com.example.freeweather.data.weatherApi.JsonRequest5days.JsonFromWeatherApi
import com.example.freeweather.data.weatherApi.JsonRequest5days.List
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var jsonAdapterRow: JsonAdapterRow
    lateinit var jsonAdapterColumn: JsonAdapterColumn
    lateinit var searchButton: ImageButton
    lateinit var editNameCity: EditText
    lateinit var searchNameGroup: LinearLayout
    lateinit var nameCity: TextView
    lateinit var iconCurrentDescription: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initRecyclerView()
        searchButton.setOnClickListener {
            searchNameGroup.visibility = LinearLayout.INVISIBLE
            loadDate(editNameCity.text.toString())
            nameCity.visibility = TextView.VISIBLE
            val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
        nameCity.setOnClickListener {
            searchNameGroup.visibility = LinearLayout.VISIBLE
            nameCity.visibility = TextView.INVISIBLE
            editNameCity.setText("")
        }

    }

    private fun loadDate(city: String) {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getJsonResultObserver().observe(this) {
            if (it != null) {
                setWeatherData(it)
                jsonAdapterColumn.list = it.list as ArrayList<List>
                jsonAdapterRow.list = it.list
                jsonAdapterRow.notifyDataSetChanged()
                jsonAdapterColumn.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Not found city name", Toast.LENGTH_LONG).show()
            }

        }
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

    private fun setWeatherData(weather: JsonFromWeatherApi) {
        findViewById<TextView>(R.id.tv_city_name).text = weather.city.name
        val currentDate = SimpleDateFormat("dd/MM/yyy")
        val date = currentDate.format(Date())
        findViewById<TextView>(R.id.tv_current_data).text = date.toString()
        val currentTime = SimpleDateFormat("HH:mm")
        val currentTemp = "${weather.list[0].main.getTempInt()}${186.toChar()}C"
        findViewById<TextView>(R.id.tv_current_time).text = currentTime.format(Date())
        findViewById<TextView>(R.id.tv_current_temp).text = currentTemp
        findViewById<TextView>(R.id.tv_descriptions).text = weather.list[0].weather[0].description
        iconCurrentDescription.setImageResource(MapperWeather.getIdResources(weather.list[0].weather[0].description))
    }

    private fun initViews() {
        searchButton = findViewById(R.id.ib_search_button)
        editNameCity = findViewById(R.id.et_name_city)
        searchNameGroup = findViewById(R.id.ll_name_group)
        nameCity = findViewById(R.id.tv_city_name)
        iconCurrentDescription = findViewById(R.id.iv_current_description)
    }

}



