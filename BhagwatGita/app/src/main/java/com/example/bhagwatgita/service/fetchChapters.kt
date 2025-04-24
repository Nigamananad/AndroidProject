package com.example.bhagwatgita.service

// Function to Call API
suspend fun fetchChapters() {
    try {
        val response = RetrofitClient.apiService.getChapters()
        println(response)
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}