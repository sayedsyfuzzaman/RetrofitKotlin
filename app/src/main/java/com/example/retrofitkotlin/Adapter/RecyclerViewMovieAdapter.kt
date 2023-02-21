package com.example.retrofitkotlin.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.Model.Movie
import com.example.retrofitkotlin.MovieList
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.ResultMovie

class RecyclerViewMovieAdapter (private val movieList: ArrayList<Movie>)
    : RecyclerView.Adapter<RecyclerViewMovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.tvMovieName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = movieList[position]
        holder.title.text = movies.title
    }
}