package com.example.likemate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.likemate.model.NumberEntity

// ✅ Room Database Class
@Database(entities = [NumberEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun numberDao(): NumberDao  // DAO ka reference

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "number_database"  // Database Name
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
