package com.example.recyclerapplication.sqlite_task_like_dislike

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.sqlite_task_like_dislike.adapter.MyAdapter
import com.example.recyclerapplication.sqlite_task_like_dislike.model.DatabaseHelper
import com.example.recyclerapplication.sqlite_task_like_dislike.model.SeriesNo

class ShowActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper
    lateinit var recyclerView: RecyclerView
    private var dataList = mutableListOf<SeriesNo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView_sqlite)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        dbHelper.insertStaticData()
        fetchDataFromDatabase()

        val adapter = MyAdapter(this, dataList)
        recyclerView.adapter = adapter
    }

    @SuppressLint("Range")
    private fun fetchDataFromDatabase() {
        val cursor = dbHelper.getAllData()
        dataList.clear()

        while (cursor.moveToNext()) {
            val id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
            val seriesNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SERIES))

            val series = SeriesNo(id, seriesNo)
            dataList.add(series)
        }

        cursor.close()
    }

}


