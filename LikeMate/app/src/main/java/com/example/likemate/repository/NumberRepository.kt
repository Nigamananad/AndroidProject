package com.example.likemate.repository

import androidx.lifecycle.LiveData
import com.example.likemate.database.NumberDao
import com.example.likemate.model.NumberEntity

//class NumberRepository(private val numberDao: NumberDao) {
//
//    // ✅ Number Insert Karna
//    suspend fun insertNumber(number: NumberEntity) {
//        numberDao.insertNumber(number)
//    }
//
//    // ✅ Sabhi Numbers Fetch Karna (RecyclerView ke liye)
//    fun getAllNumbers(): List<NumberEntity> {
//        return numberDao.getAllNumbers()
//    }
//
//    // ✅ Like Status Update Karna
//    suspend fun updateLikeStatus(id: Int, isLiked: Boolean) {
//        numberDao.updateLikeStatus(id, isLiked)
//    }
//
//    // ✅ Dislike Status Update Karna
//    suspend fun updateDislikeStatus(id: Int, isDisliked: Boolean) {
//        numberDao.updateDislikeStatus(id, isDisliked)
//    }
//
//    // ✅ Sirf Liked Numbers Fetch Karna (Dusre Screen ke liye)
//    fun getLikedNumbers(): List<NumberEntity> {
//        return numberDao.getLikedNumbers()
//    }
//
//    // ✅ Sirf Disliked Numbers Fetch Karna (Dusre Screen ke liye)
//    fun getDislikedNumbers(): List<NumberEntity> {
//        return numberDao.getDislikedNumbers()
//    }
//}

//class NumberRepository(private val numberDao: NumberDao) {
//
//    fun getAllNumbers(): LiveData<List<NumberEntity>> {
//        return numberDao.getAllNumbers()
//    }
//
//    suspend fun insertNumber(number: NumberEntity) {
//        numberDao.insertNumber(number)
//    }
//
//    suspend fun updateLikeStatus(id: Int, isLiked: Boolean) {
//        numberDao.updateLikeStatus(id, isLiked)
//    }
//
//    suspend fun updateDislikeStatus(id: Int, isDisliked: Boolean) {
//        numberDao.updateDislikeStatus(id, isDisliked)
//    }
//}
