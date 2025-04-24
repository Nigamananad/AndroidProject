package com.example.bhagwatgita.service

import com.example.bhagwatgita.model.Chapter
import retrofit2.http.GET
import retrofit2.http.Headers

interface GitaApiService {
    @Headers(
        "x-rapidapi-key: 204bb48b80msh3a8d02191a0dd78p158b59jsn86b884bb74b8",
        "x-rapidapi-host: bhagavad-gita3.p.rapidapi.com"
    )
    @GET("v2/chapters/?skip=0&limit=18")
    suspend fun getChapters(): List<Chapter>
}