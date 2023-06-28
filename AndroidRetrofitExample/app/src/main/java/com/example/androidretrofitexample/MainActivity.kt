package com.example.androidretrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidretrofitexample.adapter.UserListAdapter
import com.example.androidretrofitexample.data.Data
import com.example.androidretrofitexample.data.MainResponse
import com.example.androidretrofitexample.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var listAdapter: UserListAdapter
    lateinit var retrofit: Retrofit
    lateinit var service: ApiService
    private var userList = mutableListOf<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//https://reqres.in/api/users?page=2
//        userList.add(Data("","nnb@gmail.com","Nigam",1,"Bhuyan"))
//        userList.add(Data("","nnb@gmail.com","Nigam",2,"Bhuyan"))
//        userList.add(Data("","nnb@gmail.com","Nigam",3,"Bhuyan"))


        retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)

        listAdapter = UserListAdapter(this, userList)
        binding.recyclerItem.layoutManager = LinearLayoutManager(this)
        binding.recyclerItem.adapter = listAdapter

        loadUserList()

    }

    private fun loadUserList() {
        service.getUserList(2).enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    binding.tvpage1.text = res!!.page.toString()
                    userList = res!!.data as MutableList<Data>
                    listAdapter.setItem(userList)
                }
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "hiiii", Toast.LENGTH_SHORT).show()
            }

        })

    }
}