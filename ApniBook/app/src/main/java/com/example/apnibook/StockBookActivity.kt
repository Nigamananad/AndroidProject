package com.example.apnibook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apnibook.databinding.ActivityStockBookBinding

class StockBookActivity : AppCompatActivity() {
    lateinit var binding: ActivityStockBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStockBookBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}