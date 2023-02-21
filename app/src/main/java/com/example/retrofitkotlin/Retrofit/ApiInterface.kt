package com.example.retrofitkotlin.Retrofit

import android.graphics.pdf.PdfDocument.Page
import com.example.retrofitkotlin.MovieList
import com.example.retrofitkotlin.QuoteList
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("quotes")
    suspend fun getQuote(@Query("page") page: Int): Response<QuoteList>

    @GET("3/movie/popular")
    suspend fun getMovies(@Query("api_key") api_key: String, @Query("language") language: String, @Query("page") page: Int): Response<MovieList>

    @GET("posts")
    fun getPosts(): Call<List<JsonObject>>


    //https://api.themoviedb.org/3/movie/popular?api_key=623db847c0ed057d1e6f56726d9a865f&language=en-US&page=1
}