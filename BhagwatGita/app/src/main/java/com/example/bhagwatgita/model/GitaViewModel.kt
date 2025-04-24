package com.example.bhagwatgita.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhagwatgita.service.RetrofitClient
import kotlinx.coroutines.launch

class GitaViewModel : ViewModel() {
    val chaptersLiveData = MutableLiveData<List<Chapter>>()
    val errorLiveData = MutableLiveData<String>()

    fun fetchChapters() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getChapters()
                chaptersLiveData.postValue(response)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}
