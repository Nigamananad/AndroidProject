package com.example.flipgame.other_class

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.example.flipgame.R

object MusicManager {
    private var mediaPlayer: MediaPlayer? = null

    fun init(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context.applicationContext, R.raw.retro_bg)
            mediaPlayer?.isLooping = true // Loop the background music
        }
    }

    fun startMusic() {
        Log.d("TAG787878", "CAll 1: ")

        if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {
            Log.d("TAG787878", "CAll 2: ")
            mediaPlayer?.start()
        }
    }

    fun stopMusic() {
        mediaPlayer?.pause() // Pause music instead of stopping completely
    }

    fun releaseMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
