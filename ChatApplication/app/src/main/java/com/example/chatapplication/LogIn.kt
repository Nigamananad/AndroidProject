package com.example.chatapplication

import android.annotation.SuppressLint
import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.chatapplication.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {


    lateinit var binding: ActivityLogInBinding
    lateinit var mAuth:FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth= FirebaseAuth.getInstance()
//        edtEmail=findViewById(R.id.edt_email)
//        edtPassword=findViewById(R.id.edt_password)
//        btnLogin=findViewById(R.id.btnLogin)
//        btnSignup=findViewById(R.id.btnSignup)

        binding.btnSignup.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            var email=binding.edtEmail.text.toString().trim()
            var password=binding.edtPassword.text.toString().trim()

            login(email,password)
        }
    }

    private fun login(email: String, password: String) {

    }
}