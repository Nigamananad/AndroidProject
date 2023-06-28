package com.example.apnibookproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apnibookproject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth= FirebaseAuth.getInstance()


        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.register.setOnClickListener {

            val name = binding.textName.editText?.text.toString().trim()
            val email = binding.textEmail.editText?.text.toString().trim()
            val contact = binding.textContact.editText?.text.toString().trim()
            val password = binding.textPassword.editText?.text.toString().trim()

            //validation error show

            if (name.isEmpty()) {
                binding.textName.error = getString(R.string.name_error)
            } else {
                binding.textName.error = null
            }
            if (email.isEmpty()) {
                binding.textEmail.error = getString(R.string.email_error)
            } else {
                binding.textEmail.error = null
            }
            if (contact.isEmpty()) {
                binding.textContact.error = getString(R.string.contact_error)
            } else {
                binding.textContact.error = null
            }
            if (password.isEmpty()) {
                binding.textPassword.error = getString(R.string.password_error)
            } else {
                binding.textPassword.error = null
            }
            if (name.isNotEmpty() && email.isNotEmpty() && contact.isNotEmpty() && password.isNotEmpty()) {
                createAccount(name, email, contact, password)
                startActivity(Intent(this,LoginActivity::class.java))
//                Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
                //onBackPressedDispatcher.onBackPressed()
            }

        }
    }

    private fun createAccount(name: String, email: String, contact: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
            {
                Toast.makeText(applicationContext, "Register Successful", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "${it.exception.toString()}", Toast.LENGTH_SHORT).show()
            }
        }

    }
}