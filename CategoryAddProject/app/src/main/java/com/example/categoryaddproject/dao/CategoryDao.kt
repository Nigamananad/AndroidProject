package com.example.categoryaddproject.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.categoryaddproject.model.Category

@Dao
interface CategoryDao {
    @Insert
    fun inserUser(category: Category)

    @Query("select * from user_table")
    fun getAllElement(): MutableList<Category>

    @Delete
    fun deleteCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Query("select name from user_table")
    fun getNameElement(): MutableList<String>

}