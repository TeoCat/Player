package com.example.player.models

data class Channels(
    var channels: List<Channel>,
    val ckey: String,
    val valid: Int
)