package com.example.roompersistencedatabase

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roompersistencedatabase.data.User
import com.example.roompersistencedatabase.data.UserDatabase
import com.example.roompersistencedatabase.databinding.ActivityUserBinding
import java.lang.Exception

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db=UserDatabase.getDatabase(this)
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU)
        {
            user=intent.getParcelableExtra("NAME",User::class.java)
        }
        else{
            user=intent.getParcelableExtra("NAME")
        }


        if (user != null) {
            binding.etName.setText(user!!.name)
            binding.etEmail.setText(user!!.email)
            binding.etContact.setText(user!!.contact)
            binding.etAge.setText(user!!.age)
        }


        binding.btnAdd.setOnClickListener {
            var name = binding.etName.text.toString().trim()
            var email = binding.etEmail.text.toString().trim()
            var contact = binding.etContact.text.toString().trim()
            var age = binding.etAge.text.toString().trim()


            var userList = User(name = name, email = email, contact = contact, age = age)
            try {
                db.userDao().insertUser(userList)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }


        binding.btnDelete.setOnClickListener {

        }

    }
}