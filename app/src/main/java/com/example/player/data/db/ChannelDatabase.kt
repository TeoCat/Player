package com.example.player.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.player.models.Channel

@Database(
    entities = [Channel::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ChannelDatabase : RoomDatabase() {

    abstract fun getChannelDao(): ChannelDao

    companion object {
        @Volatile
        private var instance: ChannelDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ChannelDatabase::class.java,
                "channel_db.db"
            ).build()
    }
}