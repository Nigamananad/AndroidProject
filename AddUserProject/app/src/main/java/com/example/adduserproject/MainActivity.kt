package com.example.adduserproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.adduserproject.adapter.UserListAdapter
import com.example.adduserproject.databinding.ActivityMainBinding
import com.example.adduserproject.model.AppDatabase
import com.example.adduserproject.model.User
class MainActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: UserListAdapter
    private var userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnAddUser.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }
        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "nnb.db")
            .allowMainThreadQueries().build()
//        userList.add(User("Nigamanand", "Android Developer"))
//        userList.add(User("Nigam", "Android Developer"))
//        userList.add(User("Nigamanand", "Android Developer"))
//        userList.add(User("Nigamanand", "Android Developer"))
        mAdapter = UserListAdapter(this, userList)
        binding.recyclerItem.layoutManager = LinearLayoutManager(this)
        binding.recyclerItem.adapter = mAdapter
        updateList()
    }
    private fun updateList() {
        userList = db.userdao().getAllElement()
        mAdapter.setItem(userList)
    }

    override fun onResume() {
        super.onResume()
        if (db != null) {
            updateList()
        }
    }
}