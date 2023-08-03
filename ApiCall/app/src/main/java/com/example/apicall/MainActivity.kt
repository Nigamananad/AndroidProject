package com.example.apicall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicall.adapter.MovieListAdapter
import com.example.apicall.databinding.ActivityMainBinding
import com.example.apicall.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var listAdapter: MovieListAdapter
    private var movieList = mutableListOf<Data>()
    lateinit var retrofit: Retrofit
    lateinit var service: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        movieList.add(Data("Action","Good","","Avatar"))
//        movieList.add(Data("Action","Good","","Avatar"))
//        movieList.add(Data("Action","Good","","Avatar"))
//        movieList.add(Data("Action","Good","","Avatar"))

        //   https://www.howtodoandroid.com/movielist.json


        retrofit = Retrofit.Builder()
            .baseUrl("https://www.howtodoandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)

        listAdapter = MovieListAdapter(this, movieList)
        binding.recyclerItem.layoutManager = LinearLayoutManager(this)
        binding.recyclerItem.adapter = listAdapter

        loadMovieList()

    }

    private fun loadMovieList() {

        service.getMovieList().enqueue(object : Callback<MutableList<Data>> {
            override fun onResponse(
                call: Call<MutableList<Data>>,
                response: Response<MutableList<Data>>
            ) {

                val res = response.body()
                movieList=res!!
                listAdapter.setItem(movieList)
            }

            override fun onFailure(call: Call<MutableList<Data>>, t: Throwable) {
                Toast.makeText(applicationContext, "fail", Toast.LENGTH_SHORT).show()
            }


        })

    }
}