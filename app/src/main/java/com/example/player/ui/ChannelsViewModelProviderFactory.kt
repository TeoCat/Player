package com.example.player.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.player.data.repository.Repository


class ChannelsViewModelProviderFactory(
    val channelsRepository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChannelsViewModel(channelsRepository) as T
    }
}