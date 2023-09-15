package com.example.sqllitelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqllitelist.databinding.ActivityListViewBinding

class ListViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListViewBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: ItemAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        dbHelper.insertItem("itemName1", 0, 0)
        dbHelper.insertItem("itemName3", 0, 0)
        dbHelper.insertItem("itemName4", 0, 0)
        dbHelper.insertItem("itemName2", 0, 0)

        adapter = ItemAdapter(
            dbHelper.getAllItems(), { likeItem ->
                likeItem.likeStatus = 1
                likeItem.disLikeStatus = 0
                dbHelper.likeItem(likeItem.likeStatus, likeItem.id)
                dbHelper.dislikeItem(likeItem.disLikeStatus, likeItem.id)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Like: ${likeItem.likeStatus}", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "DisLike: ${likeItem.disLikeStatus}", Toast.LENGTH_SHORT)
                    .show()
            },
            { item ->
                item.likeStatus = 0
                item.disLikeStatus = -1
                dbHelper.likeItem(item.likeStatus, item.id)
                dbHelper.dislikeItem(item.disLikeStatus, item.id)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Like: ${item.likeStatus}", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "DisLike: ${item.disLikeStatus}", Toast.LENGTH_SHORT).show()
            }
        )
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@ListViewActivity)
        }

        binding.clearAll.setOnClickListener {
            dbHelper.clearTable(DatabaseHelper.TABLE_NAME)
            // Notify the adapter that the data has changed (list is now empty)
            adapter.updateData(emptyList())
        }

    }
}