package com.example.retrofitkotlin.Retrofit

import com.example.retrofitkotlin.Retrofit.Model.MovieDetails
import com.example.retrofitkotlin.Retrofit.Model.MovieList
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/movie/popular")
    suspend fun getMovies(@Query("api_key") api_key: String, @Query("language") language: String, @Query("page") page: Int): Response<MovieList>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int, @Query("api_key") api_key: String) : Response<MovieDetails>

//    @GET("posts")
//    fun getPosts(): Call<List<JsonObject>>


    //Movies List
    //https://api.themoviedb.org/3/movie/popular?api_key=623db847c0ed057d1e6f56726d9a865f&language=en-US&page=1

    //Movie Details
    //https://api.themoviedb.org/3/movie/640146?api_key=623db847c0ed057d1e6f56726d9a865f
}