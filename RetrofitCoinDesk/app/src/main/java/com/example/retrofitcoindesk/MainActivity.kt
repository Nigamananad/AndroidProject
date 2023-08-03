package com.example.retrofitcoindesk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitcoindesk.databinding.ActivityMainBinding
import com.example.retrofitcoindesk.model.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var retrofit: Retrofit
    lateinit var service: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.usd.setOnClickListener {
            startActivity(Intent(this, UsdActivity::class.java))
        }


    }


}
