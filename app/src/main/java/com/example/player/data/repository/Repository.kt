package com.example.player.data.repository

import com.example.player.data.api.RetrofitInstance
import com.example.player.data.db.ChannelDatabase
import com.example.player.models.Channel
import com.example.player.models.Channels
import retrofit2.Response

class Repository (val database: ChannelDatabase)
{
    suspend fun getChannels() = RetrofitInstance.api.getChannels()

    suspend fun upsert(channel: Channel) = database.getChannelDao().upsert(channel)

    fun getSavedChannels() = database.getChannelDao().getAllChannels()

    suspend fun deleteChannel(channel: Channel) = database.getChannelDao().deleteChannel(channel)

    suspend fun getAllChannels() = database.getChannelDao().getChannels()
}