package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoapplication.databinding.ActivitySeacondBinding

class SeacondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySeacondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySeacondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var city = intent.getStringExtra("CITY")

        binding.tvCity.text = city

    }
}