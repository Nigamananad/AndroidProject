package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login_and_signup.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding

    private val FILE_NAME = "User Details"
    private val KEY_EMAIL = "email"
    private val KEY_PASSWORD = "password"
    private val LOGIN_KEY = "login"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.tvForgot.setOnClickListener {
            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {

            val email = binding.edt1Email.text.toString().trim()
            val password = binding.edt1Password.text.toString().trim()

            getRecord(email, password)
        }
    }

    private fun getRecord(email: String, password: String) {
        try {
            val preferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val lEmail: String? = preferences.getString(KEY_EMAIL, null)
            val lPassword: String? = preferences.getString(KEY_PASSWORD, null)
            if (email == lEmail && password == lPassword) {

                val editor = preferences.edit()
                editor.putBoolean(LOGIN_KEY, true)
                editor.apply()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}