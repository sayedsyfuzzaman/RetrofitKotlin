package com.example.retrofitkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject

class RecyclerViewAdapter (var list:List<JsonObject>, var context: Context):

    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var ss = list.get(position).get("userId").toString()
        var some: String
//
//        holder.id.text = list.get(position).get("id").toString()
//        holder.title.text = list.get(position).get("title").toString()

        holder.name.text = "Ad"
        var s = ""

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to

        var name:TextView = view.findViewById(R.id.tvMovieName)
    }
}