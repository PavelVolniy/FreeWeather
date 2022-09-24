package com.example.freeweather.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freeweather.R
import com.example.freeweather.data.MapperWeather
import com.example.freeweather.data.weatherApi.JsonRequest5days.List

class JsonAdapterColumn : RecyclerView.Adapter<JsonAdapterColumn.JsonColumnListViewHolder>() {
    var list = ArrayList<List>()


    class JsonColumnListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_hour_column: TextView = view.findViewById(R.id.tv_hour_column)
        val iv_icon_column: ImageView = view.findViewById(R.id.iv_icon_column)
        val tv_temp_column: TextView = view.findViewById(R.id.tv_temp_column)
        fun bind(data: ColumnItem) {
            tv_hour_column.text = parseDataHour(data.hour)
            tv_temp_column.text = data.temp
//            Log.e("description-----", data.description)
            iv_icon_column.setImageResource(MapperWeather.getIdResources(data.description))
        }

        private fun parseDataHour(string: String): String {
            val regex = Regex("""(\d\d):(\d\d)""").find(string)
            val result = regex?.value
            return result.toString()
        }
    }

    private fun getListOfColumnItems(): kotlin.collections.List<ColumnItem> {
        val result = mutableListOf<ColumnItem>()
        for (item in list) {
            result.add(
                ColumnItem(
                    item.dt_txt,
                    item.weather[0].description,
                    item.main.getTempInt().toString() + 186.toChar()
                )
            )
        }
        return result.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonColumnListViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.column_view, parent, false)
        return JsonColumnListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: JsonColumnListViewHolder, position: Int) {
        val listOfColumnItems = getListOfColumnItems()
        holder.bind(listOfColumnItems[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ColumnItem(
        val hour: String,
        val description: String,
        val temp: String
    )
}