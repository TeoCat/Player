package com.example.player.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.player.models.Channel

@Dao
interface ChannelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(channel: Channel): Long

    @Query("SELECT * FROM channels")
    fun getAllChannels(): LiveData<List<Channel>>

    @Delete
    suspend fun deleteChannel(channel: Channel)

    @Query("SELECT * FROM channels")
    suspend fun getChannels(): List<Channel>
}