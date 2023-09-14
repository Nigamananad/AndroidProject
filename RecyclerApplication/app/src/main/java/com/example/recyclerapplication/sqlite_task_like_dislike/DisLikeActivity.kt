package com.example.recyclerapplication.sqlite_task_like_dislike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerapplication.R
import com.example.recyclerapplication.databinding.ActivityDisLikeBinding
import com.example.recyclerapplication.sqlite_task_like_dislike.adapter.MyAdapter
import com.example.recyclerapplication.sqlite_task_like_dislike.model.DatabaseHelper

class DisLikeActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityDisLikeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDisLikeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerDislikeItem.layoutManager = layoutManager

        adapter = MyAdapter(
            dbHelper.getDislikedItems(), { likeItem ->
                dbHelper.likeItem(likeItem.id)
                likeItem.likeStatus = 1
                likeItem.disLikeStatus = 0
                adapter.notifyDataSetChanged()
            },
            { item ->
                dbHelper.dislikeItem(item.id)
                item.likeStatus = 0
                item.disLikeStatus = -1
                adapter.notifyDataSetChanged()
            }
        )
        binding.recyclerDislikeItem.adapter = adapter
    }
}