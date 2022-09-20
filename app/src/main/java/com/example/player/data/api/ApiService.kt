package com.example.player.data.api

import com.example.player.models.Channels
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("playlist/channels.json")
    suspend fun getChannels(): Response<Channels>
}