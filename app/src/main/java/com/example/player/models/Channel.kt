package com.example.player.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "channels"
)
data class Channel(
    var key: Int? = null,
    val address: String,
    val cdn: String,
    val current: Current,
    val drm_status: Int,
    val epg_id: Int,
    val foreign_player_key: Boolean,
    val hasEpg: Boolean,
    @PrimaryKey
    val id: Int,
    val image: String,
    val is_federal: Boolean,
    val is_foreign: Boolean,
    val name_en: String,
    val name_ru: String,
    val number: Int,
    val owner: String,
    val region_code: Int,
    val tz: Int,
    val url: String,
    val url_sound: String,
    val vitrina_events_url: String,
    var fav: Boolean = false
): Serializable