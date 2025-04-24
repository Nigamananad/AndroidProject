package com.jndevelopstudio.nonetexplore.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.other_class.BaseActivity

class StartScreenActivity : BaseActivity() {
    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var btnGetStarted: Button
    private lateinit var tvTittle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        handleNotchScreen()
        lottieAnimationView = findViewById(R.id.animation_view)
        btnGetStarted = findViewById(R.id.btnGetStarted)
        tvTittle = findViewById(R.id.tvTittle)
        lottieAnimationView.playAnimation()
        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
//        super.onBackPressed()
        Log.d("TAG788", "onBackPressed: caalll ")
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val btnCancel = view.findViewById<LinearLayout>(R.id.btnCancel)
        val btnExit = view.findViewById<LinearLayout>(R.id.btnExit)
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