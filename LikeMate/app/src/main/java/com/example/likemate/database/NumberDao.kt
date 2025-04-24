package com.example.likemate.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.likemate.model.NumberEntity

@Dao
interface NumberDao {

    // ✅ 1. Number Insert Karna
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumber(number: NumberEntity)

    // ✅ 2. Sabhi Numbers Fetch Karna (RecyclerView ke liye)
    @Query("SELECT * FROM numbers ORDER BY id DESC")
    fun getAllNumbers(): LiveData<List<NumberEntity>>

    // ✅ 3. Like Status Update Karna
    @Query("UPDATE numbers SET isLiked = :isLiked, isDisliked = :isDisliked WHERE id = :id")
    suspend fun updateLikeStatus(id: Int, isLiked: Boolean, isDisliked: Boolean?)

    // ✅ 4. Dislike Status Update Karna
    @Query("UPDATE numbers SET isDisliked = :isDisliked, isLiked = :isLiked WHERE id = :id")
    suspend fun updateDislikeStatus(id: Int, isDisliked: Boolean,isLiked: Boolean?)

    // ✅ 5. Sirf Liked Numbers Fetch Karna (Dusre Screen ke liye)
    @Query("SELECT * FROM numbers WHERE isLiked = 1")
    fun getLikedNumbers(): List<NumberEntity>

    // ✅ 6. Sirf Disliked Numbers Fetch Karna (Dusre Screen ke liye)
    @Query("SELECT * FROM numbers WHERE isDisliked = 1")
    fun getDislikedNumbers(): List<NumberEntity>
}
