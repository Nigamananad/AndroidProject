package com.example.bhagwatgita.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bhagavad-gita3.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: GitaApiService = retrofit.create(GitaApiService::class.java)
}