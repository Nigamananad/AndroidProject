package com.example.recyclerapplication.sqlite_task_like_dislike

import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerapplication.R
import com.example.recyclerapplication.databinding.ActivityDashBinding

class DashActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityDashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAllList.setOnClickListener {
            startActivity(Intent(this, ShowActivity::class.java))
        }

        binding.btnLikeList.setOnClickListener {
            startActivity(Intent(this, LikeActivity::class.java))
        }
        binding.btnDislikeList.setOnClickListener {
            startActivity(Intent(this, DisLikeActivity::class.java))
        }

    }
}