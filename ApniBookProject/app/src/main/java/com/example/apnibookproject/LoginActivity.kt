package com.example.apnibookproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apnibookproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth= FirebaseAuth.getInstance()


        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.textEmail.editText?.text.toString().trim()
            val password = binding.textPassword.editText?.text.toString().trim()

            if (email.isEmpty()) {
                binding.textEmail.error = getString(R.string.email_error)
            } else {
                binding.textEmail.error = null
            }

            if (password.isEmpty()) {
                binding.textPassword.error = getString(R.string.password_error)
            } else {
                binding.textPassword.error = null
            }
            if (email.isNotEmpty()  && password.isNotEmpty()) {
                login(email,password)
//                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this, NavDrawerActivity::class.java))
            }
        }
    }

    private fun login(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isSuccessful)
            {
                navigateToNavdrawer(it.result.user)
            }
            else
            {
                Toast.makeText(applicationContext, "${it.exception.toString()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToNavdrawer(user: FirebaseUser?) {

        Toast.makeText(applicationContext, "Welcome ${user?.email}", Toast.LENGTH_SHORT).show()
        startActivity(Intent(applicationContext,NavDrawerActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        var user=mAuth.currentUser
        if (user!=null)
        {
            navigateToNavdrawer(user)
        }
    }


}