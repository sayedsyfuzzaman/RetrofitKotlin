package com.example.retrofitkotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.Adapter.RecyclerViewMovieAdapter
import com.example.retrofitkotlin.Retrofit.ApiInterface
import com.example.retrofitkotlin.Retrofit.Model.ResultMovie
import com.example.retrofitkotlin.Retrofit.RetrofitHelper
import com.example.retrofitkotlin.databinding.FragmentMoviesBinding
import kotlinx.coroutines.*


class MoviesFragment : Fragment() {
    private var listOfMovies = ArrayList<ResultMovie>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentMoviesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.rvMovie
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        val moviesListApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)

        GlobalScope.launch (Dispatchers.IO){
            val result = moviesListApi.getMovies("623db847c0ed057d1e6f56726d9a865f", "en-US", 1)
//            Log.d("Status Code", result.code().toString())
            if (result.code().toInt() == 200){
                val movieList = result.body()
                if (movieList != null) {
                    listOfMovies = movieList.results as ArrayList<ResultMovie>
                }
                withContext(Dispatchers.Main){
                    val deviceAdapter = RecyclerViewMovieAdapter(listOfMovies)
                    recyclerView.adapter = deviceAdapter
                }
            }

        }


    }
}