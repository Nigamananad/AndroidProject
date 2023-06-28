package com.example.androidroomlibrary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidroomlibrary.model.User
import com.example.androidroomlibrary.userdao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun userdao():UserDao


}