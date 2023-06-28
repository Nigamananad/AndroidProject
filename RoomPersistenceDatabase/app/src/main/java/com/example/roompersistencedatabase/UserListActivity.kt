package com.example.roompersistencedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.roompersistencedatabase.data.User
import com.example.roompersistencedatabase.data.UserDatabase
import com.example.roompersistencedatabase.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserListBinding
    lateinit var listAdapter: UserAdapter
    var user = mutableListOf<User>()
    var db=UserDatabase.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addMember.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
        listAdapter = UserAdapter(this, user)
        binding.recyclerItem.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerItem.adapter = listAdapter

        listAdapter.setOnItemClickListener(object :UserAdapter.OnItemClickListener{
            override fun onItemClicked(index: Int, user: User) {
                var intent=Intent(applicationContext,UserActivity::class.java)
                intent.putExtra("NAME",user)
                startActivity(intent)
            }
        })

    }
    //Userlist ko get karne keliye
    private fun updateList()
    {
        user=db.userDao().getUserList()
        listAdapter.setItems(user)
    }

    override fun onResume() {
        super.onResume()
        if (db!=null)
        {
            updateList()
        }
    }

}