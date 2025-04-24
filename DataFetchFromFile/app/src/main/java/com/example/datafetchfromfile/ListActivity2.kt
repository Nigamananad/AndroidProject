package com.example.datafetchfromfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ListActivity2 : AppCompatActivity() {

    lateinit var btnNext1: Button
    lateinit var btnNext2: Button
    lateinit var btnNext3: Button

    lateinit var btnNext4: Button
    lateinit var btnNext5: Button


    private val activityList = listOf(
        ListActivity1::class.java,
        ListActivity3::class.java,
        ListActivity4::class.java
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list2)
        btnNext1 = findViewById(R.id.btn_next1)
        btnNext2 = findViewById(R.id.btn_next2)
        btnNext3 = findViewById(R.id.btn_next3)

        btnNext4 = findViewById(R.id.btn_next4)
        btnNext5 = findViewById(R.id.btn_next5)

        btnNext1.setOnClickListener {
            startActivity(getRandomActivityIntent())
        }

        btnNext2.setOnClickListener {
            startActivity(getRandomActivityIntent())
        }

        btnNext3.setOnClickListener {
            startActivity(getRandomActivityIntent())
        }


        btnNext4.setOnClickListener {
            startActivity(getRandomActivityIntent())
        }

        btnNext5.setOnClickListener {
            startActivity(getRandomActivityIntent())
        }
    }

    private fun getRandomActivityIntent(): Intent {
        val randomIndex = (0 until activityList.size).random()
        return Intent(this, activityList[randomIndex])
    }
}