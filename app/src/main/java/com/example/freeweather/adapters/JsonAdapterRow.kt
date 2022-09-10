package com.example.freeweather.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.freeweather.R
import com.example.freeweather.data.weatherApi.JsonRequest5days.List

class JsonAdapterRow : RecyclerView.Adapter<JsonAdapterRow.JsonRowListViewHolder>() {
    var list = ArrayList<List>()

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
            when (data.description) {
                "  " -> iv_icon_row.setImageResource(R.drawable.ic_baseline_cloud_off_24_3)
                else -> iv_icon_row.setImageResource(R.drawable.ic_baseline_cloud_off_24_3)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonRowListViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.row_view, parent, false)
        return JsonRowListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: JsonRowListViewHolder, position: Int) {
        val listRowItems = getListRowItems()
        holder.bind(listRowItems[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun getListRowItems(): kotlin.collections.List<RowItem> {
        val result = mutableListOf<RowItem>()
        for (item in list) {
            result.add(
                RowItem(
                    parseDate(item.dt_txt),
                    item.weather[0].description,
                    item.main.humidity.toString(),
                    item.main.temp.toString(),
                    item.wind.speed.toString()
                )
            )
        }
        return result
    }

    private fun parseDate(str: String): String {
        val regex = Regex("""\d\d\d\d-\d\d-\d\d""").find(str)
        return regex?.value?.replace('-', ':')!!
    }

    class RowItem(
        val date: String,
        val description: String,
        val humidity: String,
        val temp: String,
        val wind: String
    )
}