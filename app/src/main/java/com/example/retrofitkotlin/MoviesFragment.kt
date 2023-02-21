package com.example.retrofitkotlin

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.Adapter.RecyclerViewMovieAdapter
import com.example.retrofitkotlin.Model.Movie
import com.example.retrofitkotlin.Retrofit.ApiInterface
import com.example.retrofitkotlin.Retrofit.RetrofitHelper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMovie)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)


        val quotesApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)

        var listOfMovies = ArrayList<ResultMovie>()
        val deviceList = ArrayList<Movie>()
        GlobalScope.launch {
            val result = quotesApi.getMovies("623db847c0ed057d1e6f56726d9a865f", "en-US", 1)
            val movieList = result.body()

            if (movieList != null) {
                listOfMovies = movieList.results as ArrayList<ResultMovie>
            }
            listOfMovies.forEach {
                Log.d("AAA", it.original_title)
            }
        }

        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))
        deviceList.add(Movie("Hello"))


        val deviceAdapter = RecyclerViewMovieAdapter(deviceList)
        recyclerView.adapter = deviceAdapter

//        view.findViewById<TextView>(R.id.tvMovieName).setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_moviesFragment_to_movieDetailsFragment)
//        }

    }
}