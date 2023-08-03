package com.example.retrofitcoindesk

import com.example.retrofitcoindesk.model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("currentprice.json")
    fun getList():Call<DataResponse>
}