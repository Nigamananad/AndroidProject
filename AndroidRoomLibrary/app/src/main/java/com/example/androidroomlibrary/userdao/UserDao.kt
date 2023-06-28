package com.example.androidroomlibrary.userdao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidroomlibrary.model.User
@Dao
interface UserDao {
    @Insert
    fun inserUser(user: User)
    @Query("select * from user_table")
    fun getAllElement():MutableList<User>
    @Delete
    fun deleteuser(user: User)

    @Update
    fun updateUser(user:User)
}