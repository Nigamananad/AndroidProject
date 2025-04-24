package com.example.tts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstScreen : AppCompatActivity() {
    lateinit var Name: EditText
    lateinit var btn_Next: Button

    lateinit var image1: ImageView
    lateinit var image2: ImageView
    lateinit var btnClick: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Name = findViewById(R.id.edt_name)
        btn_Next = findViewById(R.id.btn_next)
        image1 = findViewById(R.id.iv_image1)
        image2 = findViewById(R.id.iv_image2)
        btnClick = findViewById(R.id.btn_click)


        btnClick.setOnClickListener {
            image1.animate()
                .alpha(0f) // Fade out image1
                .setDuration(500) // Animation duration in milliseconds
                .withEndAction {
                    image1.visibility = View.GONE // Hide image1 after animation
                    image2.alpha = 0f // Make image2 initially invisible
                    image2.visibility = View.VISIBLE // Show image2
                    image2.animate()
                        .alpha(1f) // Fade in image2
                        .setDuration(500) // Animation duration in milliseconds
                        .start()
                }
                .start()
        }

//        btnClick.setOnClickListener {
//            image1.visibility = View.GONE // Directly hide image1
//            image2.visibility = View.VISIBLE // Make image2 visible
//            image2.scaleX = 0f // Reset scale for zoom-in effect
//            image2.scaleY = 0f
//            image2.animate()
//                .scaleX(1f) // Zoom to full size horizontally
//                .scaleY(1f) // Zoom to full size vertically
//                .setDuration(1000) // Animation duration
//                .start()
//        }



        btn_Next.setOnClickListener {
            val nameValue = Name.text.toString().trim()

            if (nameValue.isEmpty()) {
                // Show error if EditText is empty
                Name.error = "Please enter your name"
            } else {
                // Proceed to SecondActivity and pass the name value
                val intent = Intent(this, SecondScreen::class.java)
                intent.putExtra("name", nameValue)
                startActivity(intent)
            }
        }
    }
}