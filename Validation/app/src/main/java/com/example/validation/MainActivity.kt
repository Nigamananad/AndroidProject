package com.example.validation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.validation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var state = arrayOf("Gujarat","Maharastra","Odisha","Delhi","Chennai")
    lateinit var mAdapter:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,state)
        binding.autoState.setAdapter(mAdapter)

    }
}