package com.example.categoryaddproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.categoryaddproject.database.AppDatabase
import com.example.categoryaddproject.databinding.ActivityUpdateCategoryBinding
import com.example.categoryaddproject.model.Category

@Suppress("DEPRECATION")
class UpdateCategoryActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var binding: ActivityUpdateCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "category.db")
            .allowMainThreadQueries().build()

        var mCate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("DATA", Category::class.java)
        } else {
            intent.getParcelableExtra("DATA")
        }

        if (mCate != null) {
            binding.edtUpdateCategoryName.setText(mCate.name)
            binding.edtUpdateDescription.setText(mCate.description)
        }

        binding.btnUpdate.setOnClickListener {
            var name = binding.edtUpdateCategoryName.text.toString().trim()
            var desc = binding.edtUpdateDescription.text.toString().trim()

            var user = Category(name = name, description = desc, id = mCate!!.id)
            db.categorydao().updateCategory(user)
            onBackPressed()
        }

    }
}