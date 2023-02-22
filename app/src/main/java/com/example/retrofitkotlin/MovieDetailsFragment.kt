package com.example.retrofitkotlin

import android.annotation.SuppressLint
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.size.Scale
import com.example.retrofitkotlin.Adapter.RecyclerViewMovieAdapter
import com.example.retrofitkotlin.Retrofit.ApiInterface
import com.example.retrofitkotlin.Retrofit.Model.ResultMovie
import com.example.retrofitkotlin.Retrofit.RetrofitHelper
import com.example.retrofitkotlin.databinding.FragmentMovieDetailsBinding
import com.example.retrofitkotlin.databinding.FragmentMoviesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private var movieId:Int = 0
    private var POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        movieId = arguments?.getInt("id")!!
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieDetailsApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)

        GlobalScope.launch (Dispatchers.IO){
            val result = movieDetailsApi.getMovieDetails(id=movieId,"623db847c0ed057d1e6f56726d9a865f")
//            Log.d("Status Code", result.code().toString())
            if (result.code().toInt() == 200){
                val movieDetails = result.body()
                withContext(Dispatchers.Main){
                    if (movieDetails != null) {
                        binding.tvMovieTitle.text = movieDetails.original_title
                        binding.tvMovieTagLine.text = movieDetails.tagline
                        binding.tvMovieDateRelease.text = movieDetails.release_date
                        binding.tvMovieRating.text = movieDetails.vote_average.toString()
                        binding.tvMovieRuntime.text = movieDetails.runtime.toString()+" hr"
                        binding.tvMovieBudget.text = movieDetails.budget.toString()
                        binding.tvMovieRevenue.text = movieDetails.revenue.toString()
                        binding.tvMovieOverview.text = movieDetails.overview

                        val moviePosterURL = POSTER_BASE_URL+movieDetails.poster_path

                        binding.imgMovie.load(moviePosterURL) {
                            crossfade(true)
                            placeholder(R.drawable.poster_placeholder)
                            scale(Scale.FILL)
                        }

                        binding.imgMovieBg.load(moviePosterURL) {
                            crossfade(true)
                            placeholder(R.drawable.poster_placeholder)
                            scale(Scale.FILL)
                        }
                    }
                }
            }

        }
    }

}