package com.example.datafetchfromfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayImagesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_images)
        recyclerView = findViewById(R.id.recyclerView)




        val imagePaths = intent.getStringArrayListExtra("imagePaths") ?: arrayListOf()
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = ImageAdapter(imagePaths)
    }
}