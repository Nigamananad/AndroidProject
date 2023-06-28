package com.example.androidretrofitexample.data

data class MainResponse(
    val data:List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)