package com.example.androidretrofitexample

import com.example.androidretrofitexample.data.MainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("users")
    fun getUserList(@Query("page") page: Int): Call<MainResponse>
}