package com.example.yukigames.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(

    arrayList : ArrayList<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle

) : FragmentStateAdapter(fragmentManager,lifecycle) {

    private val fragmentList = arrayList
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}