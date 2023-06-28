package com.example.androidroomlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.androidroomlibrary.database.AppDatabase
import com.example.androidroomlibrary.databinding.ActivityAddUserBinding
import com.example.androidroomlibrary.model.User

class AddUserActivity : AppCompatActivity() {
    lateinit var db:AppDatabase
    lateinit var binding: ActivityAddUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityAddUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        db=Room.databaseBuilder(this,AppDatabase::class.java, name = "dixit.db").allowMainThreadQueries().build()
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener {
            var name=binding.edtName.text.toString().trim()
            var des=binding.edtDescription.text.toString().trim()

            inserUser(name,des)
        }


    }

    private fun inserUser(name: String, des: String) {
        var user=User(name = name, description = des)
        db.userdao().inserUser(user)
        Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }
}