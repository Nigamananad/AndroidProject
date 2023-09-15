package com.example.sqllitelist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqllitelist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAllList.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }

        binding.btnLikeList.setOnClickListener {
            startActivity(Intent(this, LikeListActivity::class.java))
        }
        binding.btnDislikeList.setOnClickListener {
            startActivity(Intent(this, DislikeListActivity::class.java))
        }
    }
}