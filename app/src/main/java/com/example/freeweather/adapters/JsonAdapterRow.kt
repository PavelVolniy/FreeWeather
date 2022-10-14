package com.example.freeweather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freeweather.R
import com.example.freeweather.data.MapperWeather
import com.example.freeweather.data.weatherApi.JsonRequest5days.List

class JsonAdapterRow : RecyclerView.Adapter<JsonAdapterRow.JsonRowListViewHolder>() {
    var list = ArrayList<List>()
    var listItems = arrayListOf<RowItem>()

    class JsonRowListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_Name_Date = view.findViewById<TextView>(R.id.tv_name_date)
        val iv_icon_row = view.findViewById<ImageView>(R.id.iv_icon_row)
        val tv_humidity = view.findViewById<TextView>(R.id.tv_humidity)
        val tv_row_temp = view.findViewById<TextView>(R.id.tv_row_temp)
        val tv_row_wind = view.findViewById<TextView>(R.id.tv_row_wind)
        fun bind(data: RowItem) {
            tv_Name_Date.text = data.date
            tv_humidity.text = data.humidity
            tv_row_temp.text = data.temp
            tv_row_wind.text = data.wind
            iv_icon_row.setImageResource(MapperWeather.getIdResources(data.description))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonRowListViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.row_view, parent, false)
        return JsonRowListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: JsonRowListViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        listItems = getListRowItems() as ArrayList<RowItem>
        return listItems.size
    }

    private fun getListRowItems(): MutableList<RowItem> {
        val result = mutableListOf<RowItem>()
        for (item in list) {
            if (parseHour(item.dt_txt) == "12:00") {
                result.add(
                    RowItem(
                        parseDate(item.dt_txt),
                        item.weather[0].description,
                        item.main.humidity.toString() + "%",
                        item.main.getTempInt().toString() + 186.toChar(),
                        item.wind.speed.toString() + "м/с"
                    )
                )
            }
        }
        return result
    }

    private fun parseHour(str: String): String {
        val regex = Regex("""\d\d:\d\d""").find(str)
        return regex?.value.toString()
    }

    private fun parseDate(str: String): String {
        val regex = Regex("""(\d\d\d\d-)(\d\d)-(\d\d)""").find(str)
        return "${regex?.groupValues!![3]}:${regex.groupValues[2]}"
    }

    class RowItem(
        val date: String,
        val description: String,
        val humidity: String,
        val temp: String,
        val wind: String
    )
}