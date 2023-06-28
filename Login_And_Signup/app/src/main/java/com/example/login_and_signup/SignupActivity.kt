package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.example.login_and_signup.databinding.ActivitySignupBinding
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {


    lateinit var binding: ActivitySignupBinding

    private val FILE_NAME="User Details"
    private  val KEY_NAME="name"
    private  val KEY_EMAIL="email"
    private  val KEY_CONTACT="contact"
    private  val KEY_PASSWORD="password"


    var regex = ("^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignup.setOnClickListener {
            val  name = binding.edtName.text.toString().trim()
            val  email = binding.edtEmail.text.toString().trim()
            val  contact = binding.edtContact.text.toString().trim()
            val  password = binding.edtPassword.text.toString().trim()
            val  cpassword = binding.edtCPassword.text.toString().trim()

            saveRecord(name,email,contact,password)

            resetFocus()

            if (name.isEmpty()) {
                binding.edtName.error = "Enter Valid Name"
                setError(binding.edtName, "Enter Valid Name")
            } else if (!isValidEmail(email)) {
                binding.edtEmail.error = "Enter Valid Email"
                setError(binding.edtEmail, "Enter Valid Email")
            } else if (!isValidContact(contact)) {
                binding.edtContact.error = "Enter Valid Contact"
                setError(binding.edtContact, "Enter Valid Contact")
            } else if (!isValidPassword(password)) {
                binding.edtPassword.error = "Enter Valid Password"
                setError(binding.edtPassword, "Enter Valid Password")
            } else if (cpassword != password) {
                binding.edtCPassword.error = "Password Mismatch"
                setError(binding.edtCPassword, "Password Mismatch")
            } else {
                Toast.makeText(this, "All Done", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }
        }
        binding.tvLogin.setOnClickListener {
            val  intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveRecord(name: String, email: String, contact: String, password: String) {
        try {
            val  preferences=getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
            val editor=preferences.edit()
            editor.putString(KEY_NAME,name)
            editor.putString(KEY_EMAIL,email)
            editor.putString(KEY_CONTACT,contact)
            editor.putString(KEY_PASSWORD,password)
            editor.apply()

//            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
//            onBackPressed()



       /*     var name :String?=preferences.getString(KEY_NAME,null)
            var email:String?=preferences.getString(KEY_EMAIL,null)
            var contact:Int=preferences.getInt(KEY_CONTACT,0)
            var password:String?=preferences.getString(KEY_PASSWORD,null)*/

        }catch (e:Exception)
        {
            e.printStackTrace()
        }
    }

    private fun resetFocus() {
        binding.edtName.setBackgroundResource(R.drawable.edt_background)
        binding.edtEmail.setBackgroundResource(R.drawable.edt_background)
        binding.edtContact.setBackgroundResource(R.drawable.edt_background)
        binding.edtPassword.setBackgroundResource(R.drawable.edt_background)
        binding.edtCPassword.setBackgroundResource(R.drawable.edt_background)
    }

    private fun setError(editText: EditText, errorMsg: String) {
        editText.setBackgroundResource(R.drawable.edt_background_error)
        editText.requestFocus()
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidContact(contact: String): Boolean {
        return contact.length == 10
    }

    private fun isValidPassword(password: String): Boolean {
        return Pattern.compile(regex).matcher(password).matches()
    }
}