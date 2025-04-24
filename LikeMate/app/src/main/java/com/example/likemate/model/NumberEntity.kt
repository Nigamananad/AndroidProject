package com.example.likemate.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "numbers")
data class NumberEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generated unique ID
    val number: Long,   // User ka input number
    val isLiked: Boolean = false,   // Like Status
    val isDisliked: Boolean = false // Dislike Status
)
