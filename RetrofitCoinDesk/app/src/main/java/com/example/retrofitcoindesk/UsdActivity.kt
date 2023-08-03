package com.example.retrofitcoindesk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitcoindesk.databinding.ActivityUsdBinding
import com.example.retrofitcoindesk.model.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class UsdActivity : AppCompatActivity() {
    lateinit var binding: ActivityUsdBinding
    lateinit var retrofit: Retrofit
    lateinit var service: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUsdBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //https://api.coindesk.com/v1/bpi/currentprice.json

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.coindesk.com/v1/bpi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)

        loadData()

    }

    private fun loadData() {
        service.getList().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    val ress=response.body()

                    //screen refresh
                    val swipeRefreshLayout = binding.swiprefresh
                    binding.swiprefresh.setOnRefreshListener {
                        binding.tvRate.text = ress!!.bpi.USD.rate
                        swipeRefreshLayout.isRefreshing = false

                    }

                    binding.tvTittle.text = res!!.bpi.USD.code
                    res.bpi.USD.description.also { binding.tvDescription.text = it }
                    binding.tvRate.text = res.bpi.USD.rate
                    binding.tvSymbol.text = res.bpi.USD.symbol

                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }
}