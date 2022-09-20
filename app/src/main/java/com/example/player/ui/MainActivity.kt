package com.example.player.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.player.databinding.ActivityMainBinding
import com.example.player.adapters.ViewPagerAdapter
import com.example.player.data.db.ChannelDatabase
import com.example.player.data.repository.Repository
import com.example.player.util.Constants
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ChannelsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val newsRepository = Repository(ChannelDatabase(this))
        val viewModelProviderFactory = ChannelsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ChannelsViewModel::class.java)
        initial()
    }

    private fun initial() {
        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                Constants.ALL_CHANNELS -> {
                    tab.text = "Все"
                }
                Constants.FAVOURITES -> {
                    tab.text = "Избранное"
                }
            }
        }.attach()
    }
}