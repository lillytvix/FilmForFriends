package com.example.filmforfriends

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object Database{

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(MoviesService::class.java)
}

interface MoviesService{
    @GET("discover/movie?sort_by=popularity.desc&api_key=795d87bfa31bd215fc78ae4423e39543")
    fun requestmovies(): Call<MovieResults>
}