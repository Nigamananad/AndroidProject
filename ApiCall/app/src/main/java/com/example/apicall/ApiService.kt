package com.example.apicall

import com.example.apicall.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movielist.json")
    fun getMovieList():Call<MutableList<Data>>
}