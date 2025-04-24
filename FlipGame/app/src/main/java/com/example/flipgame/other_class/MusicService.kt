package com.example.flipgame.other_class

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.example.flipgame.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private var isPaused = false
    private var pausePosition = 0

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.retro_bg) // Apna music file rakho res/raw me
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "PLAY" -> playMusic()
            "PAUSE" -> pauseMusic()
            "STOP" -> stopMusic()
            else -> playMusic()
        }
        return START_STICKY
    }

    private fun playMusic() {
        Log.d("TAG_TY", "playMusic: 1 ")
        if (isPaused) {
            Log.d("TAG_TY", "playMusic: 2 ")
            mediaPlayer.seekTo(pausePosition)
            mediaPlayer.start()
            isPaused = false
        } else if (!mediaPlayer.isPlaying) {
            Log.d("TAG_TY", "playMusic: 3 ")
            mediaPlayer.start()
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            pausePosition = mediaPlayer.currentPosition
            mediaPlayer.pause()
            isPaused = true
        }
    }

    private fun stopMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            stopSelf()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
