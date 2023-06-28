package com.example.paigeradapter.Package

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.paigeradapter.Model.item
import com.example.paigeradapter.databinding.ItemIntoLayoutBinding

class IntoPagerAdapter(var context: Context, var itemList: MutableList<item>) : PagerAdapter() {

    lateinit var binding: ItemIntoLayoutBinding

    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding= ItemIntoLayoutBinding.inflate(LayoutInflater.from(context),container,false)

        var item=itemList[position]
        binding.tvTitle.text=item.title
        binding.tvDesc.text=item.desc
        binding.ivThumbnail.setImageResource(item.image)

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}