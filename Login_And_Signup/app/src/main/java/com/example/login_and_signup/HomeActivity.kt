package com.example.login_and_signup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_and_signup.adapter.UserListAdapter
import com.example.login_and_signup.databinding.ActivityHomeBinding
import com.example.login_and_signup.model.User

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding

    lateinit var listAdapter: UserListAdapter
    private var userList= mutableListOf<User>()
    private val FILE_NAME = "User Details"
    private val KEY_NAME = "name"
    private val KEY_EMAIL = "email"
    private val KEY_CONTACT = "contact"


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


binding.topNav.setOnMenuItemClickListener {menuItems ->
    when(menuItems.itemId)
    {
        R.id.item_logout->{
            val preference=getSharedPreferences("User Details", MODE_PRIVATE)
            val editor=preference.edit()
            editor.remove("login")
            editor.apply()
            Toast.makeText(this, "LogedOut", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoginActivity::class.java))
            true
        }
        else-> false
    }
}

        try {
            val preference = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val name: String? = preference.getString(KEY_NAME, null)
            val email: String? = preference.getString(KEY_EMAIL, null)
            val contact: String? = preference.getString(KEY_CONTACT, null)

            binding.tvView1.text = """
                Name : $name
                Email : $email
                Contact : $contact
            """.trimIndent()


        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.button2.setOnClickListener {
            val preference=getSharedPreferences("User Details", MODE_PRIVATE)
            val editor=preference.edit()
            editor.remove("login")
            editor.apply()
            Toast.makeText(this, "LogedOut", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoginActivity::class.java))
        }

//        userList.add(User("JayKumar Joshi","jay@email.com","7896541234"))
//        userList.add(User("Susmita Choudhary","susmita@email.com","7896861234"))
//        userList.add(User("Rukshar Qureshi","rukshar@email.com","9469871236"))
//        userList.add(User("Saroj Panda","saroj@email.com","8596321478"))
//        userList.add(User("Ashish Panigrahi","Ashish@email.com","85632147968"))
//        userList.add(User("Vivekanand bhuyan","vivek@email.com","9856541234"))
//        userList.add(User("Sonali Polleyi","sonali@email.com","7217541234"))
//        userList.add(User("Krupali upadhyay","krupali@email.com","7996541753"))
//        userList.add(User("Nigamanand bhuyan","nigam@email.com","9597536482"))
//
//        listAdapter = UserListAdapter(this, userList)
//        binding.recyclerItem.layoutManager = LinearLayoutManager(this)
//        binding.recyclerItem.adapter = listAdapter

    }
}