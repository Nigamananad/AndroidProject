package com.example.apnibook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apnibook.Adapter.HomeListAdapter
import com.example.apnibook.databinding.ActivityHomeBinding
import com.example.apnibook.databinding.ActivityMainBinding
import com.example.apnibook.dataclass.Home

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
//    lateinit var hAdapter:HomeListAdapter
//    var homeList= mutableListOf<Home>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        homeList.add(Home(1,R.drawable.client,"Client Book"))
//        homeList.add(Home(2,R.drawable.dollar,"Business Book"))
//        homeList.add(Home(3,R.drawable.warehouse,"Stock Book"))
//        homeList.add(Home(4,R.drawable.wallet,"Expense Book"))


//        hAdapter= HomeListAdapter(this,homeList)
//        binding.horizontalView.layoutManager=GridLayoutManager(this,2)
//        binding.horizontalView.adapter=hAdapter
    }
}