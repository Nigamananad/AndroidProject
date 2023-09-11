package com.example.androidretrofitexample

import com.example.androidretrofitexample.data.MainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("users?page=2")
    fun getUserList(): Call<MainResponse>
}