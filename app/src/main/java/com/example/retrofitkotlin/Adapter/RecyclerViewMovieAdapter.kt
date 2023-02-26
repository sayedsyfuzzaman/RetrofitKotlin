package com.example.retrofitkotlin.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.retrofitkotlin.MovieDetailsFragment
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.Retrofit.Model.MovieList
import com.example.retrofitkotlin.Retrofit.Model.ResultMovie
import com.example.retrofitkotlin.databinding.FragmentMoviesBinding
import com.example.retrofitkotlin.databinding.ItemRowBinding
import com.google.android.material.imageview.ShapeableImageView

class RecyclerViewMovieAdapter (private val movieList: ArrayList<ResultMovie>)
    : RecyclerView.Adapter<RecyclerViewMovieAdapter.MovieViewHolder>(){

    private var POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
    private lateinit var mainView:View

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var movieName: TextView = itemView.findViewById(R.id.tvMovieName)
        var movieRate:TextView = itemView.findViewById(R.id.tvRate)
        var movieLanguage:TextView = itemView.findViewById(R.id.tvLang)
        var movieReleaseDate:TextView = itemView.findViewById(R.id.tvReleaseDate)
        var moviePoster: ShapeableImageView = itemView.findViewById(R.id.imgMoviePoster)
        var root:ConstraintLayout = itemView.findViewById(R.id.infoRoot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        mainView = view
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = movieList[position]
        holder.movieName.text = movies.original_title
        holder.movieRate.text = movies.vote_average.toString()
        holder.movieLanguage.text = movies.original_language
        holder.movieReleaseDate.text = movies.release_date
        val moviePosterURL = POSTER_BASE_URL+movies.poster_path
        holder.moviePoster.load(moviePosterURL){
            crossfade(true)
            placeholder(R.drawable.poster_placeholder)
            scale(Scale.FILL)
        }

        val args = Bundle().apply {
            putInt("id", movies.id)
        }

        holder.itemView.setOnClickListener {
            Navigation.findNavController(mainView).navigate(R.id.action_moviesFragment_to_movieDetailsFragment, args)
        }
    }
}