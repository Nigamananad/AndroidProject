package com.example.whatsappchat.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.whatsappchat.Fragment.CallFragment
import com.example.whatsappchat.Fragment.ChatFragment
import com.example.whatsappchat.Fragment.StatusFragment


private const val NUM_TABS=3

class MyPagerAdapter(manager:FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(manager,lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->ChatFragment()
            1->StatusFragment()
            else->CallFragment()
        }
    }
}