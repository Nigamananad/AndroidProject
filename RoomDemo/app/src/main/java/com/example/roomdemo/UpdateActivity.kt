package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdemo.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var name = intent.getStringExtra("NAME")
        var email = intent.getStringExtra("EMAIL")

        binding.edtUpdateName.setText(name)
        binding.edtUpdateEmail.setText(email)

    }
}