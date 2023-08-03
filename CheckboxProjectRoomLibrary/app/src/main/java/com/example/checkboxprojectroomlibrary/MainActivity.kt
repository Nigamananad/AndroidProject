package com.example.checkboxprojectroomlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.checkboxprojectroomlibrary.databinding.ActivityMainBinding
import com.example.checkboxprojectroomlibrary.model.AppDatabase
import com.example.checkboxprojectroomlibrary.model.Data

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var selectedIcon: MutableList<Int>
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "nnb.db")
            .allowMainThreadQueries().build()

        selectedIcon = mutableListOf()


        val checkBox1 = binding.checkBox
        val checkBox2 = binding.checkBox2

        checkBox1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                selectedIcon.add(R.drawable.baseline_heart_broken_24)
            } else {
                selectedIcon.remove(R.drawable.baseline_heart_broken_24)
            }
        }
        checkBox2.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                selectedIcon.add(R.drawable.baseline_star_24)
            } else {
                selectedIcon.remove(R.drawable.baseline_star_24)
            }
        }

        binding.submit.setOnClickListener {
            var icon1 = binding.checkBox.toString()
            var icon2 = binding.checkBox2.toString()
            var name = binding.edtName.text.toString().trim()

            insertIcon(icon1, icon2, name)

        }

    }

    private fun insertIcon(icon1: String, icon2: String, name: String) {

        var icon = Data(iconResourceId = icon1, name = name)
        db.userdao().insertSelectedIcon(icon)
        Toast.makeText(applicationContext, "insert succes", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }
}