package com.example.adduserproject.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {


    abstract fun userdao(): UserDao
}