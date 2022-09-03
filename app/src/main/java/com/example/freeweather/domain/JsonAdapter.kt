package com.example.freeweather.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeweather.R
import com.example.freeweather.data.weatherApi.JsonRequest5days.List

class JsonAdapter : RecyclerView.Adapter<JsonAdapter.JsonListViewHolder>() {
    private val list = ArrayList<List>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonListViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.row_view, parent, false)
        return JsonListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: JsonListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class JsonListViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bind(data: List){

        }
    }
}

