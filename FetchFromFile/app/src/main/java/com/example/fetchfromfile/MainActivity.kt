package com.example.fetchfromfile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fetchfromfile.fetch_from_file.AudioListActivity

class MainActivity : AppCompatActivity() {
    lateinit var fetchFromFile:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            fetchFromFile=findViewById(R.id.btn_fetch_from_file)


        fetchFromFile.setOnClickListener {
            startActivity(Intent(this,AudioListActivity::class.java))
        }
    }
}