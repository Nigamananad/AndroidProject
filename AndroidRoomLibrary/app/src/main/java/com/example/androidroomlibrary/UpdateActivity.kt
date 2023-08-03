package com.example.androidroomlibrary

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.androidroomlibrary.database.AppDatabase
import com.example.androidroomlibrary.databinding.ActivityUpdateBinding
import com.example.androidroomlibrary.model.User

@Suppress("DEPRECATION")
class UpdateActivity : AppCompatActivity() {

    lateinit var db: AppDatabase

    lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "dixit.db")
            .allowMainThreadQueries().build()

        // var user = intent.getParcelableExtra("USER",User::class.java)
        var user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("USER", User::class.java)
        } else {
            intent.getParcelableExtra("USER")
        }

        if (user != null) {
            binding.edtName.setText(user.name)
            binding.edtDescription.setText(user.description)



        }

        binding.btnUpdate.setOnClickListener {

            var name = binding.edtName.text.toString().trim()
            var desc = binding.edtDescription.text.toString().trim()

            var mUser = User(name = name, description = desc, id = user!!.id)

            db.userdao().updateUser(mUser)
            onBackPressed()
        }


    }
}