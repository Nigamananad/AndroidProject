package com.example.recyclerapplication.sqlite_task_like_dislike

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.databinding.ActivityShowBinding
import com.example.recyclerapplication.sqlite_task_like_dislike.adapter.MyAdapter
import com.example.recyclerapplication.sqlite_task_like_dislike.model.DatabaseHelper
import com.example.recyclerapplication.sqlite_task_like_dislike.model.SeriesNo

class ShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: MyAdapter
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityShowBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        dbHelper.insertItem("itemName1", 0, 0)
        dbHelper.insertItem("itemName3", 0, 0)
        dbHelper.insertItem("itemName4", 0, 0)
        dbHelper.insertItem("itemName2", 0, 0)


        adapter = MyAdapter(dbHelper.getAllItems(), { likeItem ->
            likeItem.likeStatus = 1
            likeItem.disLikeStatus = 0
            dbHelper.likeItem(likeItem.likeStatus,likeItem.id)
            dbHelper.dislikeItem(likeItem.disLikeStatus, likeItem.id)
            adapter.notifyDataSetChanged()
        }, { item ->
            item.likeStatus = 0
            item.disLikeStatus = -1
            dbHelper.likeItem(item.likeStatus, item.id)
            dbHelper.dislikeItem(item.disLikeStatus, item.id)
            adapter.notifyDataSetChanged()
        })

        binding.apply {
            recyclerViewSqlite.adapter = adapter
            recyclerViewSqlite.layoutManager = LinearLayoutManager(this@ShowActivity)
        }
        binding.clearAll.setOnClickListener {
            dbHelper.clearTable(DatabaseHelper.TABLE_NAME)
            // Notify the adapter that the data has changed (list is now empty)
            adapter.updateData(emptyList())
        }
        
    }

}


