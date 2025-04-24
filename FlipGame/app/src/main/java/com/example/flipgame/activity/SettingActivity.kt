package com.example.flipgame.activity

import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flipgame.R
import com.example.flipgame.other_class.MusicHelper
import com.example.flipgame.other_class.MusicManager
import com.example.flipgame.other_class.SharedPreferencesHelper

class SettingActivity : AppCompatActivity() {
    lateinit var switchMusic:Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        switchMusic=findViewById(R.id.switchMusic)

        val isMusicOn = SharedPreferencesHelper.getMusicState(this)
        switchMusic.isChecked = isMusicOn
        switchMusic.setOnCheckedChangeListener { _, isChecked ->
            SharedPreferencesHelper.saveMusicState(this, isChecked)
            Log.d("TAG787878", "onCreate Setting Screen: $isChecked ")
            if (isChecked) {
                Log.d("TAG_TY", "playMusic: 4 ")
                MusicHelper.startMusicService(this)
            } else {
                Log.d("TAG_TY", "playMusic: 5 ")
                MusicHelper.pauseMusicService(this)
            }
        }
    }
}