package com.example.player.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.player.ui.fragments.ChannelsFragment
import com.example.player.ui.fragments.FavouritesFragment
import com.example.player.util.Constants.Companion.ALL_CHANNELS
import com.example.player.util.Constants.Companion.AMOUNT_FRAGMENTS

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return AMOUNT_FRAGMENTS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            ALL_CHANNELS -> ChannelsFragment()
            else -> FavouritesFragment()
        }
    }
}