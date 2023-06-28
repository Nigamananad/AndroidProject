package com.example.apnibook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apnibook.Adapter.HomeListAdapter
import com.example.apnibook.databinding.ActivityNavigationDrawerBinding
import com.example.apnibook.dataclass.Home

class NavigationDrawerActivity : AppCompatActivity() {

    lateinit var binding: ActivityNavigationDrawerBinding
    lateinit var drawerToggle: ActionBarDrawerToggle
    lateinit var hAdapter:HomeListAdapter
    var homeList= mutableListOf<Home>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolBar.title="Home"
        setSupportActionBar(binding.toolBar)

        drawerToggle=ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolBar,R.string.nav_open,R.string.nav_close)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()


        homeList.add(Home(1,R.drawable.client,"Client Book"))
        homeList.add(Home(2,R.drawable.dollar,"Business Book"))
        homeList.add(Home(3,R.drawable.warehouse,"Stock Book"))
        homeList.add(Home(4,R.drawable.wallet,"Expense Book"))


        hAdapter= HomeListAdapter(this,homeList)
        binding.horizontalView.layoutManager= GridLayoutManager(this,2)
        binding.horizontalView.adapter=hAdapter
    }


}