package com.example.login_and_signup1

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_and_signup1.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    var regex = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val contact = binding.etPhone.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val cpassword = binding.etCpassword.text.toString().trim()

            if (name.isEmpty()) {
                binding.etName.error = "Fill Name"
            } else if (!isValidEmail(email)) {
                binding.etEmail.error = "Enter Valid Format Email"
            }
            else if(!isValidContact(contact))
            {
                binding.etPhone.error="Invalid contact"
            }
            else if(!isValidPassword(password))
            {
                binding.etPassword.error="Invalid Password"
            }
            else if (password!=cpassword)
            {
                binding.etCpassword.error="Invalid Confirm Password"
            }
            else
            {
                Toast.makeText(this, "All Done.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidContact(contact: String): Boolean {
        return contact.length == 10
    }
    private fun isValidPassword(password:String):Boolean{
        return Pattern.matches(regex,password)
    }
}