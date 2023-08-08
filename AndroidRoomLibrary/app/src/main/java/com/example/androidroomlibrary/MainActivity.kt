package com.example.androidroomlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.androidroomlibrary.adapter.UserListAdapter
import com.example.androidroomlibrary.database.AppDatabase
import com.example.androidroomlibrary.databinding.ActivityMainBinding
import com.example.androidroomlibrary.model.User

class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var mAdapter: UserListAdapter
    private var userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btn_add)
        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }
        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "dixit.db")
            .allowMainThreadQueries().build()

//        userList.add(User(1, "TV", "LED"))
//        userList.add(User(2, "Mobile", "Led"))
//        userList.add(User(3, "Cable", "LED"))
//        userList.add(User(4, "PenDrive", "LED"))

        mAdapter = UserListAdapter(this, userList)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_item)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        updatelist()

    }

    private fun updatelist() {
        userList = db.userdao().getAllElement()
        mAdapter.setitem(userList)
    }

    override fun onResume() {
        super.onResume()
        if (db != null) {
            updatelist()
        }
    }
}