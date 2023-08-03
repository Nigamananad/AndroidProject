package com.example.retrofitcoindesk.model

data class DataResponse(
    val bpi: Bpi,
    val chartName: String,
    val disclaimer: String,
    val time: Time
)