package com.example.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.permissions.databinding.ActivityImagePickerBinding

class ImagePickerActivity : AppCompatActivity() {

    private val REQ_GALLERY=101
    lateinit var binding: ActivityImagePickerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageview.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
         var intent= Intent()
       // intent.action=Intent.ACTION_PICK
        intent.action=Intent.ACTION_GET_CONTENT
        intent.type="image/*"
        startActivityForResult(intent,REQ_GALLERY)
    }
}