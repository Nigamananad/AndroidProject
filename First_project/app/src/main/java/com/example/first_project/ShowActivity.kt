package com.example.first_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class ShowActivity : AppCompatActivity() {

    val resView: TextView
        get() = findViewById(R.id.res_view)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

     /*   var user = intent.getParcelableExtra<User>("USER")

      if (user != null) {
            resView.text = """
            name : ${user.name}
            email : ${user.email}
            age : ${user.age}
            address:${user.address}
        """.trimIndent()*/


            var show=intent.getStringExtra("LANG")
            resView.text="$show"
        }


    }