package com.example.a3dgame

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a3dgame.classes.GameView
import com.example.a3dgame.classes.JoystickView

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        // Main layout
        val layout = FrameLayout(this)

        // GameView (Snake movement)
        val gameView = GameView(this, null)

        // JoystickView
        val joystickView = JoystickView(this, null)

        // Joystick ko bottom-left par rakhne ke liye LayoutParams
        val joystickParams = FrameLayout.LayoutParams(400, 400).apply {
            gravity = Gravity.BOTTOM or Gravity.START
            leftMargin = 50
            bottomMargin = 50
        }
        // Joystick ka input GameView ke snake ko bhejne ke liye
        joystickView.joystickListener = { dx, dy ->
            gameView.snake.setDirection(dx, dy)
        }

        // Layout me views add karna
        layout.addView(gameView)
        layout.addView(joystickView, joystickParams)

        setContentView(layout)
    }
}