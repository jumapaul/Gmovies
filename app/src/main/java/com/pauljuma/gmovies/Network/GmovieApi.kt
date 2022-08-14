package com.pauljuma.gmovies.Network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GmovieApi {
    private val client = OkHttpClient.Builder().build()

    val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create()).client(client).build()

    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}