package com.example.paigeradapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewpager.widget.PagerAdapter
import com.example.paigeradapter.Model.item
import com.example.paigeradapter.Package.IntoPagerAdapter
import com.example.paigeradapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var itemList = mutableListOf<item>()

    lateinit var mAdapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        itemList.add(
            item(
                1,
                "First Movie",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                R.drawable.img1
            )
        )
        itemList.add(
            item(
                2,
                "Second Movie",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                R.drawable.img2
            )
        )
        itemList.add(
            item(
                1,
                "Third Movie",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                R.drawable.img3
            )
        )

        mAdapter=IntoPagerAdapter(this,itemList)
        binding.viewPager.adapter=mAdapter
    }
}