package com.example.flipgame.other_class

import android.content.Context
import android.content.Intent

object MusicHelper {
    fun startMusicService(context: Context) {
        val intent = Intent(context, MusicService::class.java)
        intent.action = "PLAY"
        context.startService(intent)
    }

    fun pauseMusicService(context: Context) {
        val intent = Intent(context, MusicService::class.java)
        intent.action = "PAUSE"
        context.startService(intent)
    }

    fun stopMusicService(context: Context) {
        val intent = Intent(context, MusicService::class.java)
        intent.action = "STOP"
        context.startService(intent)
    }
}
