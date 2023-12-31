package com.example.checkboxprojectroomlibrary.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert

import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM selected_icons")
    fun getAllSelectedIcons(): LiveData<List<Data>>

    @Insert
     fun insertSelectedIcon(selectedIcon: Data)
}