package com.example.adduserproject.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("select * from user_table")
    fun getAllElement(): MutableList<User>

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}