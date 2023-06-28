package com.example.whatsappchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.whatsappchat.Adapter.MyPagerAdapter
import com.example.whatsappchat.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var pagerAdapter: MyPagerAdapter
    var tabs = arrayOf("Chat", "Status", "Call")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        pagerAdapter= MyPagerAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter=pagerAdapter


        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = tabs[position]
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.newgroup -> {
                Toast.makeText(this, "fshdfhjsdfj", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.newbroadcast -> {
                true
            }
            R.id.linkeddevice -> {
                true
            }
            R.id.starredmessage -> {
                true
            }
            R.id.payment -> {
                true
            }
            R.id.setting -> {
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }


    }

}