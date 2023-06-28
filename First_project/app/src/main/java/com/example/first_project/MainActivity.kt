package com.example.first_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val btnNext:Button
    get()=findViewById(R.id.btn_next)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNext.setOnClickListener {
            var intent=Intent(this,FirstActivity::class.java)
            startActivity(intent)
        }
    }
}