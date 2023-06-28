package com.example.roompersistencedatabase.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("select * from `user-table`")
    fun getUserList(): MutableList<User>

    @Update
    fun updateUser(user: User)

    @Query("update `user-table` set id=:id,name=:name,email=:email,contact=:contact,age=:age")
    fun updateUserRecord(id: Int, name: String, email: String, contact: String, age: String)

    @Delete
    fun deleteUser(user:User)

    @Query("select * from `user-table` where id=:uid")
    fun deleteUserRecord(uid:Int):User

}