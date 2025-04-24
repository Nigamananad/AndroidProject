package com.example.flipgame.activity

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.flipgame.R
import com.example.flipgame.other_class.BaseActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class StartScreenActivity : BaseActivity() {
    lateinit var lottieAnimationView: LottieAnimationView
    lateinit var btnGetStarted: Button
    lateinit var tvTittle:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        handleNotchScreen()
        lottieAnimationView = findViewById(R.id.animation_view)
        btnGetStarted = findViewById(R.id.btnGetStarted)
//        tvTittle = findViewById(R.id.tvTittle)
        lottieAnimationView.playAnimation()
        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        Log.d("TAG788", "onBackPressed: caalll ")
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val btnCancel = view.findViewById<LinearLayout>(R.id.btnCancel)
        val btnExit =view.findViewById<LinearLayout>(R.id.btnExit)
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        btnExit.setOnClickListener {
            dialog.dismiss()
            finishAffinity()
        }
        dialog.setCancelable(false)
        dialog.setContentView(view)

        dialog.show()
    }
}