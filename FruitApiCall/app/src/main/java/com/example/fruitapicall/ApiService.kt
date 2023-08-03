package com.example.fruitapicall

import com.example.fruitapicall.model.FruitResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("all")
    fun getfruitList(): Call<MutableList<FruitResponseItem>>

}