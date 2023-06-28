package com.example.first_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    val btnNext1: Button
        get() = findViewById(R.id.btn_next1)

    val etext_Name: EditText
        get() = findViewById(R.id.etext_name)

    val etext_Email: EditText
        get() = findViewById(R.id.etext_email)

    val etext_Age: EditText
        get() = findViewById(R.id.etext_age)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        btnNext1.setOnClickListener {
            var name = etext_Name.text.toString().trim()
            var email = etext_Email.text.toString().trim()
            var age = if (etext_Age.text.toString().trim().isEmpty())
                0
            else
                etext_Age.text.toString().trim().toInt()

            Toast.makeText(this,"""
                
                name:$name
                email:$email
                age:$age
                
            """.trimIndent(),Toast.LENGTH_SHORT).show()

            var user = User(name, email, age)

            var intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("USER", user)
            startActivity(intent)
        }
    }
}