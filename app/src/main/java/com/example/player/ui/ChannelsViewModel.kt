package com.example.player.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.player.data.repository.Repository
import com.example.player.models.Channel
import com.example.player.models.Channels
import com.example.player.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ChannelsViewModel(
    val channelsRepository: Repository
) : ViewModel() {

    val allChannels: MutableLiveData<Resource<Channels>> = MutableLiveData()

    init {
        getAllChannels()
    }

    fun getAllChannels() = viewModelScope.launch {
        val response = channelsRepository.getChannels()
        val savedChannels = channelsRepository.getAllChannels()
        if (response.body() != null) {
            for (i in response.body()!!.channels) {
                for (j in savedChannels.indices) {
                    if (i.name_ru == savedChannels[j].name_ru) {
                        i.fav = savedChannels[j].fav
                    }
                }
            }
        }
        allChannels.postValue(handleChannelsResponse(response))
    }

    private fun handleChannelsResponse(response: Response<Channels>): Resource<Channels> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveChannel(channel: Channel) = viewModelScope.launch {
        channelsRepository.upsert(channel)
    }

    fun getSavedChannels() = channelsRepository.getSavedChannels()

    fun deleteChannel(channel: Channel) = viewModelScope.launch {
        channelsRepository.deleteChannel(channel)
    }
}