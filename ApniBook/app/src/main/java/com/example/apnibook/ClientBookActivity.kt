package com.example.apnibook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apnibook.databinding.ActivityClientBookBinding

class ClientBookActivity : AppCompatActivity() {

    lateinit var binding: ActivityClientBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityClientBookBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}