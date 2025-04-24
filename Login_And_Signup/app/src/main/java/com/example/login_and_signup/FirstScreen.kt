package com.example.login_and_signup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstScreen : AppCompatActivity() {
    lateinit var Name: EditText
    lateinit var btn_Next: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)
        Name=findViewById(R.id.edt_name)
        btn_Next=findViewById(R.id.btn_next)

        btn_Next.setOnClickListener {
            val nameValue = Name.text.toString().trim()

            if (nameValue.isEmpty()) {
                // Show error if EditText is empty
                Name.error = "Please enter your name"
            } else {
                // Proceed to SecondActivity and pass the name value
                val intent = Intent(this, SecondScreen::class.java)
                intent.putExtra("name", nameValue)
                startActivity(intent)
            }
        }
    }
}