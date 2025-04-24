package com.example.flipgame.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flipgame.R
import com.example.flipgame.adapter.HomeGListAdapter
import com.example.flipgame.model.GameClass
import com.example.flipgame.other_class.BaseActivity
import com.example.flipgame.other_class.MusicHelper
import com.example.flipgame.other_class.MusicManager
import com.example.flipgame.other_class.MyApp
import com.example.flipgame.other_class.SharedPreferencesHelper

class HomeScreenActivity : BaseActivity() {
    lateinit var gListAdapter: HomeGListAdapter
    var gList = mutableListOf<GameClass>()
    lateinit var recyclerView: RecyclerView
    lateinit var btnSetting:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        handleNotchScreen()
        recyclerView = findViewById(R.id.rv_gList)
        btnSetting=findViewById(R.id.btnSetting)

        val isMusicOn = SharedPreferencesHelper.getMusicState(this)
        Log.d("TAG787878", "onCreate HomeScreen: $isMusicOn ")
        if (isMusicOn) {
            MusicHelper.startMusicService(this)
        }
        btnSetting.setOnClickListener {
            startActivity(Intent(this,SettingActivity::class.java))
        }
        gList.add(GameClass(1, R.drawable.tictactoe, ""))
        gList.add(GameClass(2, R.drawable.dotboxes, ""))
        gList.add(GameClass(3, R.drawable.flipmatch, ""))
        gList.add(GameClass(4, R.drawable.puzzle, ""))

        gListAdapter = HomeGListAdapter(this, gList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = gListAdapter
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) { // App close ho raha hai tabhi stop karo
            MusicHelper.stopMusicService(this)
        } else {
            MusicHelper.pauseMusicService(this)
        }
    }

    override fun onResume() {
        super.onResume()
        val isMusicOn = SharedPreferencesHelper.getMusicState(this)
        if (isMusicOn && MyApp.isAppInBackground) {
            MusicHelper.startMusicService(this) // Wapas open hone pe music resume
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        MusicHelper.stopMusicService(this) // App close hone par music band
    }
}