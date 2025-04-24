package com.example.datafetchfromfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var btnNext1: Button
    lateinit var btnNext2: Button
    lateinit var btnNext3: Button

    lateinit var btnNext4: Button
    lateinit var btnNext5: Button

    lateinit var btn_list_activity1: TextView
    lateinit var btn_list_activity2: TextView
    lateinit var btn_list_activity3: TextView
    lateinit var btn_list_activity4: TextView

    private val activityList = listOf(
        ListActivity1::class.java,
        ListActivity2::class.java,
        ListActivity3::class.java,
        ListActivity4::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnNext1 = findViewById(R.id.btn_next1)
        btnNext2 = findViewById(R.id.btn_next2)
        btnNext3 = findViewById(R.id.btn_next3)

        btnNext4 = findViewById(R.id.btn_next4)
        btnNext5 = findViewById(R.id.btn_next5)

        btn_list_activity1 = findViewById(R.id.btn_list_activity1)
        btn_list_activity2 = findViewById(R.id.btn_list_activity2)
        btn_list_activity3 = findViewById(R.id.btn_list_activity3)
        btn_list_activity4 = findViewById(R.id.btn_list_activity4)

        btn_list_activity1.setOnClickListener {
            startActivity(Intent(this, ListActivity1::class.java))
        }
        btn_list_activity2.setOnClickListener {
            startActivity(Intent(this, ListActivity2::class.java))
        }
        btn_list_activity3.setOnClickListener {
            startActivity(Intent(this, ListActivity3::class.java))
        }
        btn_list_activity4.setOnClickListener {
            startActivity(Intent(this, ListActivity4::class.java))
        }

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
//        val randomIndex = (0 until activityList.size).random()
//        return Intent(this, activityList[randomIndex])
        val randomIndex = (0 until activityList.size).random()
        val intent = Intent(this, activityList[randomIndex])
        intent.putExtra("randomValue", 1)
        return intent
    }
}