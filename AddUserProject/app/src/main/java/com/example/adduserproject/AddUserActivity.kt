package com.example.adduserproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.adduserproject.databinding.ActivityAddUserBinding
import com.example.adduserproject.model.AppDatabase
import com.example.adduserproject.model.User

class AddUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddUserBinding
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "nnb.db")
            .allowMainThreadQueries().build()
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            var name = binding.edtName.text.toString().trim()
            var depart = binding.edtDepartment.text.toString().trim()
            insertUser(name, depart)
        }

    }

    private fun insertUser(name: String, depart: String) {
        var user = User(name = name, department = depart)
        db.userdao().insertUser(user)
        Toast.makeText(applicationContext, "Record Saved", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }
}