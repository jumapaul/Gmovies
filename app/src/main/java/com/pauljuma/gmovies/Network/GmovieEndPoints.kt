package com.pauljuma.gmovies.Network

import com.pauljuma.gmovies.MyData.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GmovieEndPoints {

    @GET("3/movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") key: String): Call<MoviesResponse>

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") key: String): Call<MoviesResponse>

    @GET("3/movie/popular")
    fun getPopularMovies(@Query("api_key") key: String): Call<MoviesResponse>
}