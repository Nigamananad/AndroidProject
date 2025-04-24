package com.jndevelopstudio.nonetexplore.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.VideoView
import androidx.cardview.widget.CardView
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.other_class.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity() {
    private lateinit var cvSplashCardLogo: CardView
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