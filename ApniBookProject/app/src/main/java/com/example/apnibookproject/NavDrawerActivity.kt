package com.example.apnibookproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apnibookproject.Adapter.HomeListAdapter
import com.example.apnibookproject.databinding.ActivityNavDrawerBinding
import com.example.apnibookproject.dataclass.Home
import com.google.firebase.auth.FirebaseAuth

class NavDrawerActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavDrawerBinding
    lateinit var hAdapter: HomeListAdapter
    lateinit var mAuth: FirebaseAuth
    var homeList = mutableListOf<Home>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth= FirebaseAuth.getInstance()

        homeList.add(Home(1, R.drawable.client, "Client Book"))
        homeList.add(Home(2, R.drawable.warehouse, "Stock Book"))
        homeList.add(Home(3, R.drawable.dollar, "Business Book"))
        homeList.add(Home(4, R.drawable.wallet, "Expense Book"))

        hAdapter = HomeListAdapter(this, homeList)
        binding.recyclerview.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerview.adapter = hAdapter

        binding.topToolbar.setNavigationOnClickListener {
            binding.navDrawer.open()

        }
        binding.navigationView.setNavigationItemSelectedListener {
            it.isCheckable = true
            binding.navDrawer.close()
            true
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    Toast.makeText(this, "Clicked Home", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.item_logout->{
                    mAuth.signOut()
                    startActivity(Intent(applicationContext,LoginActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
}