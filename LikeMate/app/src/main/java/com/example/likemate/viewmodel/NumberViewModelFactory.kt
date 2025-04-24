package com.example.likemate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.likemate.database.NumberDao

class NumberViewModelFactory(private val numberDao: NumberDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NumberViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NumberViewModel(numberDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
