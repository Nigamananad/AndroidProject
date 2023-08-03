package com.example.categoryaddproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.categoryaddproject.database.AppDatabase
import com.example.categoryaddproject.databinding.ActivityAddCategoryBinding
import com.example.categoryaddproject.model.Category

class AddCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddCategoryBinding
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "category.db")
            .allowMainThreadQueries().build()
        setContentView(binding.root)

       binding.btnAdd.setOnClickListener {
           var name = binding.edtCategoryName.text.toString().trim()
           var desc = binding.edtDescription.text.toString().trim()
           insertCategory(name, desc)
       }
    }

    private fun insertCategory(name: String, desc: String) {
        var data = Category(name = name, description = desc)
        db.categorydao().inserUser(data)
        Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }
}