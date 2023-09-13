package com.example.recyclerapplication.sqlite_task_like_dislike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.sqlite_task_like_dislike.adapter.MyAdapter
import com.example.recyclerapplication.sqlite_task_like_dislike.model.SeriesNo

class LikeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter // MyAdapter ko aap apne Adapter ke naam ke sath replace karenge
    private val likedItems = mutableListOf<SeriesNo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)

        recyclerView = findViewById(R.id.recycler_like_item)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

//        val bundle = intent.extras
//        if (bundle != null) {
//            likedItems.addAll(bundle.getParcelableArrayList("likedItems")!!)
//        }
//        recyclerView.adapter = adapter

    }
}