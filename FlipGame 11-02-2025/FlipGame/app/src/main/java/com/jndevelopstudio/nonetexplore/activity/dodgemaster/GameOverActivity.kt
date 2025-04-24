package com.jndevelopstudio.nonetexplore.activity.dodgemaster

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.activity.HomeScreenActivity
import com.jndevelopstudio.nonetexplore.other_class.BaseActivity
import com.sanchayastudio.savethebunny.GameView

class GameOverActivity : BaseActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val tvPoints = findViewById<TextView>(R.id.tvPoint)
        val tvHighest = findViewById<TextView>(R.id.tvHighest)
        val btnHome = findViewById<LinearLayout>(R.id.btnRestart)
        val btnRestart = findViewById<LinearLayout>(R.id.btnHome)
        val points = intent.extras!!.getInt("POINTS")
        tvPoints.text = "" + points
        sharedPreferences = getSharedPreferences("MY_FILE", MODE_PRIVATE)
        var highest = sharedPreferences.getInt("HIGHEST", 0)
        if (points > highest) {
            highest = points
            val editor = sharedPreferences.edit()
            editor.putInt("HIGHEST", highest)
            editor.commit()
        }
        tvHighest.text = "" + highest

        btnHome.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
        }

        btnRestart.setOnClickListener {
            startGame()
            finish()
        }

    }

    private fun startGame() {
        val gameView = GameView(this)
        setContentView(gameView)
    }
}