package com.example.login_and_signup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    private val FILE_NAME = "User Details"
    private val LOGIN_KEY = "login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread(Runnable {
            Thread.sleep(3000)
            var prefrence = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            var status = prefrence.getBoolean(LOGIN_KEY, false)

            if (status) {

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }).start()

    }
}