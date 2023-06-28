package com.example.login_and_signup1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import com.example.login_and_signup1.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    var regex = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    val tvSignup: TextView
        get() = findViewById(R.id.tt_signup)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (!isValidEmail(email)) {
                binding.editTextEmail.error = "Enter Valid Format Email"
            }
            else if (!isValidPassword(password)){
                binding.editTextPassword.error="Invalid Password"
            }
        }

        tvSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password:String):Boolean{
        return Pattern.matches(regex,password)
    }
}