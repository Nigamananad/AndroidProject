package com.example.adduserproject.volley_library_example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.adduserproject.R
import com.example.adduserproject.volley_library_example.model.CourseRVModal
import com.squareup.picasso.Picasso

class ImagePagerAdapter(private val context: Context, private val imagesList: List<CourseRVModal>):
    PagerAdapter() {
    override fun getCount(): Int {
        return imagesList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.view_pager_item, container, false) as ViewGroup
        val imageView = layout.findViewById<ImageView>(R.id.iv_image)

        Picasso.get().load(imagesList[position].courseImg).into(imageView)

        container.addView(layout)
        return layout
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}