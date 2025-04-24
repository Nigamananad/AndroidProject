package com.example.likemate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.likemate.database.NumberDao
import com.example.likemate.model.NumberEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class NumberViewModel(private val repository: NumberRepository) : ViewModel() {
//
//    private val _numbers = MutableLiveData<List<NumberEntity>>()
//    val numbers: LiveData<List<NumberEntity>> get() = _numbers
//
//    init {
//        loadNumbers()
//    }
//
//    // ✅ Sabhi Numbers Load Karna
//    fun loadNumbers() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _numbers.postValue(repository.getAllNumbers())
//        }
//    }
//
//    // ✅ Number Insert Karna
//    fun insertNumber(number: NumberEntity) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.insertNumber(number)
//            loadNumbers() // List Update Karna
//        }
//    }
//
//    // ✅ Like Status Update Karna
//    fun updateLikeStatus(id: Int, isLiked: Boolean) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateLikeStatus(id, isLiked)
//            loadNumbers() // List Update Karna
//        }
//    }
//
//    // ✅ Dislike Status Update Karna
//    fun updateDislikeStatus(id: Int, isDisliked: Boolean) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateDislikeStatus(id, isDisliked)
//            loadNumbers() // List Update Karna
//        }
//    }
//
//    // ✅ Sirf Liked Numbers Fetch Karna
//    fun getLikedNumbers(): LiveData<List<NumberEntity>> {
//        val likedNumbers = MutableLiveData<List<NumberEntity>>()
//        viewModelScope.launch(Dispatchers.IO) {
//            likedNumbers.postValue(repository.getLikedNumbers())
//        }
//        return likedNumbers
//    }
//
//    // ✅ Sirf Disliked Numbers Fetch Karna
//    fun getDislikedNumbers(): LiveData<List<NumberEntity>> {
//        val dislikedNumbers = MutableLiveData<List<NumberEntity>>()
//        viewModelScope.launch(Dispatchers.IO) {
//            dislikedNumbers.postValue(repository.getDislikedNumbers())
//        }
//        return dislikedNumbers
//    }
//}
class NumberViewModel(private val numberDao: NumberDao) : ViewModel() {

    val numbers: LiveData<List<NumberEntity>> = numberDao.getAllNumbers()

    // ✅ Number Insert Function
    fun insertNumber(number: NumberEntity) {
        viewModelScope.launch {
            numberDao.insertNumber(number)
        }
    }

        // ✅ Sirf Liked Numbers Fetch Karna
    fun getLikedNumbers(): LiveData<List<NumberEntity>> {
        val likedNumbers = MutableLiveData<List<NumberEntity>>()
        viewModelScope.launch(Dispatchers.IO) {
            likedNumbers.postValue(numberDao.getLikedNumbers())
        }
        return likedNumbers
    }



    // ✅ Like Update Function
//    fun updateLikeStatus(id: Int, isLiked: Boolean) {
//        viewModelScope.launch {
//            numberDao.updateLikeStatus(id, isLiked)
//        }
//    }
//
//    // ✅ Dislike Update Function
//    fun updateDislikeStatus(id: Int, isDisliked: Boolean) {
//        viewModelScope.launch {
//            numberDao.updateDislikeStatus(id, isDisliked)
//        }
//    }

    // ✅ Like Button Click Pe Logic
    fun onLikeClicked(id: Int, isLiked: Boolean) {
        viewModelScope.launch {
//            numberDao.updateLikeStatus(id, isLiked)
//            numberDao.updateDislikeStatus(id, !isLiked) // Dislike ko false karna hai agar Like select ho gaya
            numberDao.updateLikeStatus(id, isLiked, if (isLiked) false else null)
        }
    }

    // ✅ Dislike Button Click Pe Logic
    fun onDislikeClicked(id: Int, isDisliked: Boolean) {
        viewModelScope.launch {
//            numberDao.updateLikeStatus(id, !isDisliked) // Like ko false karna hai agar Dislike select ho gaya
//            numberDao.updateDislikeStatus(id, isDisliked)
            numberDao.updateDislikeStatus(id, isDisliked, if (isDisliked) false else null)
        }
    }
}
