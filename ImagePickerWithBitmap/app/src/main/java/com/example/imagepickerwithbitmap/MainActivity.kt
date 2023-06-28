package com.example.imagepickerwithbitmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.imagepickerwithbitmap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val REQ_GALLERY = 101
    private val REQ_CAMERA = 102

    val galleryPermissionContract=registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if (it){
            pickImageFromGallery()
        }
        else
        {

        }
    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivImage.setOnClickListener {
            galleryPermissionContract.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }

    }

    private fun pickImageFromGallery() {
        val intent = Intent()
        // intent.action=Intent.ACTION_PICK
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent, REQ_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_GALLERY) {
            if (resultCode == RESULT_OK && data != null){
                var imageUri=data.data
                binding.ivImage.setImageURI(imageUri)
            }
        } else if (requestCode == REQ_CAMERA) {

        }
    }
}