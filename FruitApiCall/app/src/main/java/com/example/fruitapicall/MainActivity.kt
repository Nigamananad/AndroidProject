package com.example.fruitapicall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fruitapicall.adapter.FruitListAdapter
import com.example.fruitapicall.databinding.ActivityMainBinding
import com.example.fruitapicall.model.FruitResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listAdapter: FruitListAdapter
    lateinit var retrofit: Retrofit
    lateinit var service: ApiService
    private var fruitList = mutableListOf<FruitResponseItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //  https://fruityvice.com/api/fruit/all


//        fruitList.add(FruitResponseItem("Rosaceae", "Rubus", 1, "Raspberry", "Rosales", ""))
//        fruitList.add(FruitResponseItem("Rosaceae", "Rubus", 2, "Raspberry", "Rosales", ""))
//        fruitList.add(FruitResponseItem("Rosaceae", "Rubus", 3, "Raspberry", "Rosales", ""))
//        fruitList.add(FruitResponseItem("Rosaceae", "Rubus", 4, "Raspberry", "Rosales", ""))
//        fruitList.add(FruitResponseItem("Rosaceae", "Rubus", 5, "Raspberry", "Rosales", ""))

        retrofit = Retrofit.Builder()
            .baseUrl("https://fruityvice.com/api/fruit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)

        listAdapter = FruitListAdapter(this, fruitList)
        binding.recyclerItem.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerItem.adapter = listAdapter

        loadfruitList()

    }

    private fun loadfruitList() {
        service.getfruitList().enqueue(object : Callback<MutableList<FruitResponseItem>> {
            override fun onResponse(
                call: Call<MutableList<FruitResponseItem>>,
                response: Response<MutableList<FruitResponseItem>>
            ) {
                var res = response.body()
                fruitList = res!!
                listAdapter.setItem(fruitList)
            }

            override fun onFailure(call: Call<MutableList<FruitResponseItem>>, t: Throwable) {
                Toast.makeText(applicationContext, "fail", Toast.LENGTH_SHORT).show()
            }

        })
    }
}