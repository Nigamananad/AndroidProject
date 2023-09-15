package com.example.sqllitelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqllitelist.databinding.ActivityDislikeListAcivityBinding

class DislikeListActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: ItemAdapter
    private lateinit var binding: ActivityDislikeListAcivityBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDislikeListAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        // Initialize the RecyclerView
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        adapter = ItemAdapter(
            dbHelper.getDislikedItems(), { likeItem ->
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
        binding.recyclerView.adapter = adapter


    }
}