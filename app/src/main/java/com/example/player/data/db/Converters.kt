package com.example.player.data.db

import androidx.room.TypeConverter
import com.example.player.models.Current

class Converters {

    @TypeConverter
    fun fromCurrent(current: Current): String {
        return current.title
    }

    @TypeConverter
    fun toCurrent(title: String): Current {
        return Current(title)
    }
}