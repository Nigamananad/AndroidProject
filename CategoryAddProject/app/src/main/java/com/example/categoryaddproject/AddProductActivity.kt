package com.example.categoryaddproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.room.Room
import com.example.categoryaddproject.database.AppDatabase
import com.example.categoryaddproject.databinding.ActivityAddProductBinding
import com.example.categoryaddproject.model.Category

class AddProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddProductBinding
    lateinit var db: AppDatabase

    lateinit var spin: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "category.db")
            .allowMainThreadQueries().build()

        var cate = db.categorydao().getNameElement()
        spin = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cate)
        binding.autoCategory.setAdapter(spin)

    }
}