package com.example.mixplayer.adpater

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mixplayer.fragment.AudioFragment
import com.example.mixplayer.fragment.ImageFragment
import com.example.mixplayer.fragment.VideoFragment

class MediaPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AudioFragment()
            1 -> VideoFragment()
            2 -> ImageFragment()
            else -> Fragment()
        }
    }
}