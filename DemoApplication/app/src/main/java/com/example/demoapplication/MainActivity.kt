package com.example.demoapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.demoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var city = arrayOf("Gujarat", "Maharastra", "Odisha", "Delhi", "Chennai")
    lateinit var mAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.submit.setOnClickListener {
            var city = binding.textCategory.editText?.text.toString().trim()

            var intent = Intent(this, SeacondActivity::class.java)
            intent.putExtra("CITY", city)
            startActivity(intent)
        }

        mAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, city)
        binding.autoCity.setAdapter(mAdapter)

    }
}