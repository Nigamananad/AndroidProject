package com.example.flipgame.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.os.postDelayed
import com.example.flipgame.R
import com.example.flipgame.other_class.BaseActivity

class SplashScreenActivity : BaseActivity() {
    lateinit var cvSplashCardLogo: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        handleNotchScreen()
        val videoView: VideoView = findViewById(R.id.videoView)
        cvSplashCardLogo = findViewById(R.id.cvSplashCardLogo)

        val videoUri = Uri.parse("android.resource://$packageName/raw/splashscreenbg")
        videoView.setVideoURI(videoUri)
        videoView.start()

        Handler().postDelayed({
            animatelogo()
        }, 2000)

        Handler().postDelayed({
            startActivity(Intent(this, StartScreenActivity::class.java))
            finish()
        }, 4000)

    }

    private fun animatelogo() {
        cvSplashCardLogo.visibility = View.VISIBLE
        cvSplashCardLogo.alpha = 0f
        cvSplashCardLogo.animate()
            .alpha(1f)
            .setDuration(1000)
            .setListener(null)
    }
}