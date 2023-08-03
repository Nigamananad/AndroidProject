package com.example.adduserproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.adduserproject.databinding.ActivityUpdateBinding
import com.example.adduserproject.model.AppDatabase
import com.example.adduserproject.model.User

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "nnb.db")
            .allowMainThreadQueries().build()
        setContentView(binding.root)

        var user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("DATA", User::class.java)
        } else {
            intent.getParcelableExtra("DATA")
        }

        if (user != null) {
            binding.edtUpdateName.setText(user.name)
            binding.edtUpdateDepartment.setText(user.department)
        }

        binding.btnUpdate.setOnClickListener {
            var name = binding.edtUpdateName.text.toString().trim()
            var depat = binding.edtUpdateDepartment.text.toString().trim()

            var user = User(name = name, department = depat, id = user!!.id)
            db.userdao().updateUser(user)
            onBackPressed()
        }

    }
}