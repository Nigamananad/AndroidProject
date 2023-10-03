package com.example.recyclerapplication.sqlite_task_like_dislike

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.databinding.ActivityLikeBinding
import com.example.recyclerapplication.sqlite_task_like_dislike.adapter.MyAdapter
import com.example.recyclerapplication.sqlite_task_like_dislike.model.DatabaseHelper
import com.example.recyclerapplication.sqlite_task_like_dislike.model.OnItemInteractionListener
import com.example.recyclerapplication.sqlite_task_like_dislike.model.SeriesNo

class LikeActivity : AppCompatActivity() {

    private lateinit var   dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityLikeBinding
    private lateinit var adapter: MyAdapter // MyAdapter ko aap apne Adapter ke naam ke sath replace karenge
    private val likedItems = mutableListOf<SeriesNo>()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLikeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerLikeItem.layoutManager = layoutManager

//        adapter = MyAdapter(
//            dbHelper.getNewLikedItems(), { likeItem ->
//                likeItem.likeStatus = 1
//                likeItem.disLikeStatus = 0
//                dbHelper.likeItem(likeItem.likeStatus, likeItem.id)
//                dbHelper.dislikeItem(likeItem.disLikeStatus, likeItem.id)
//                adapter.notifyDataSetChanged()
//            },
//            { item ->
//                item.likeStatus = 0
//                item.disLikeStatus = 1
//                dbHelper.likeItem(item.likeStatus, item.id)
//                dbHelper.dislikeItem(item.disLikeStatus, item.id)
//                adapter.notifyDataSetChanged()
//            }
//        )

        adapter = MyAdapter(
            dbHelper.getNewLikedItems(),
            object : OnItemInteractionListener {
                override fun onLikeClicked(item: SeriesNo) {
                    item.likeStatus = 1
                    item.disLikeStatus = 0
                    dbHelper.likeItem(item.likeStatus, item.id)
                    dbHelper.dislikeItem(item.disLikeStatus, item.id)
                    adapter.notifyDataSetChanged()
                }

                override fun onDislikeClicked(item: SeriesNo) {
                    item.disLikeStatus = 1
                    item.likeStatus = 0
                    dbHelper.dislikeItem(item.disLikeStatus, item.id)
                    dbHelper.likeItem(item.likeStatus, item.id)
                    adapter.notifyDataSetChanged()
                }
            }
        )

        binding.recyclerLikeItem.adapter = adapter
    }
}