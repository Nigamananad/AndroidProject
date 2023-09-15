package com.example.recyclerapplication.sqlite_task_like_dislike

import android.annotation.SuppressLint
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDisLikeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerDislikeItem.layoutManager = layoutManager

        adapter = MyAdapter(
            dbHelper.getDislikedItems(), { likeItem ->
                likeItem.likeStatus = 1
                likeItem.disLikeStatus = 0
                dbHelper.likeItem(likeItem.likeStatus, likeItem.id)
                dbHelper.dislikeItem(likeItem.disLikeStatus, likeItem.id)
                adapter.notifyDataSetChanged()
            },
            { item ->
                item.likeStatus = 0
                item.disLikeStatus = -1
                dbHelper.likeItem(item.likeStatus, item.id)
                dbHelper.dislikeItem(item.disLikeStatus, item.id)
                adapter.notifyDataSetChanged()
            }
        )
        binding.recyclerDislikeItem.adapter = adapter
    }
}