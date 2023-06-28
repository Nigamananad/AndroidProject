package com.example.bottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavigationview.databinding.ActivityMainBinding
import com.example.bottomnavigationview.fragments.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolBar)

        val badge = binding.bottomNavigate.getOrCreateBadge(R.id.notification)
        badge.isVisible = true
        badge.number = 24

        addFragment(HomeFragment(), "Home")

        binding.bottomNavigate.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.home -> {
                    addFragment(HomeFragment(), "Home")
                    true
                }
                R.id.order -> {
                    addFragment(OrderFragment(), "My Order")
                    true
                }
                R.id.profile -> {
                    addFragment(ProfileFragment(), "Profile")
                    true
                }
                R.id.notification -> {
                    addFragment(NotificationFragment(), "Notification")
                    true
                }
                R.id.search -> {
                    addFragment(SearchFragment(), "Search")
                    true
                }
                else -> false
            }
        }
    }

    private fun addFragment(fragment: Fragment, tittle: String) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
        binding.toolBar.title = tittle
    }
}