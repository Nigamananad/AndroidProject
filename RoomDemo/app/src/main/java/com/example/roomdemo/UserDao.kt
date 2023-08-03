package com.example.roomdemo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("select * from `user-table`")
    fun getAllElement():MutableList<User>


}