package com.example.apnibook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread {
            Thread.sleep(2000)
            startActivity(Intent(this,NavigationDrawerActivity::class.java))
        }.start()
    }
}