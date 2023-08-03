package com.example.categoryaddproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.categoryaddproject.dao.CategoryDao
import com.example.categoryaddproject.model.Category

@Database(entities = [Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categorydao(): CategoryDao
}