package com.example.adduserproject.volley_library_example

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.example.adduserproject.R
import com.example.adduserproject.volley_library_example.adapter.ImagePagerAdapter
import com.example.adduserproject.volley_library_example.model.CourseRVModal
import com.squareup.picasso.Picasso

class NextActivity : AppCompatActivity() {
    //    lateinit var image: ImageView
    lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        viewPager = findViewById(R.id.viewPager)

        val imagesList = intent.getParcelableArrayListExtra<CourseRVModal>("IMAGES_LIST")

//        image = findViewById(R.id.iv_image)

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("IMAGE", CourseRVModal::class.java)
        } else {
            intent.getParcelableExtra("IMAGE")
        }

        if (data != null && imagesList!!.isNotEmpty()) {
//            Picasso.get().load(data.courseImg).into(image)
            val adapter = ImagePagerAdapter(this, imagesList)
            viewPager.adapter = adapter
        }


    }
}