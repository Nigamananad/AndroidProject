package com.example.roomdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.roomdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "nnb.db")
            .allowMainThreadQueries().build()

        binding.btnAdd.setOnClickListener {
            var name = binding.edtName.text.toString().trim()
            var email = binding.edtEmail.text.toString().trim()


            var intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("NAME", name)
            intent.putExtra("EMAIL", email)
            startActivity(intent)

            insertUser(name, email)
        }
        binding.update.setOnClickListener {
            var intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
        }

    }

    private fun insertUser(name: String, email: String) {

        var user = User(name = name, email = email)
        db.userdao().insertUser(user)
        Toast.makeText(applicationContext, "insert success", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }
}