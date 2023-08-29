package com.example.recyclerapplication.layout_params


import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding



class LayoutParamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.recyclerapplication.R.layout.activity_layout_params)

        val shopButton: Button = findViewById(com.example.recyclerapplication.R.id.shop)
        val ashimButton: Button = findViewById(com.example.recyclerapplication.R.id.ashim)
        val nigamButton: Button = findViewById(com.example.recyclerapplication.R.id.nigam)
        val sagarButton: Button = findViewById(com.example.recyclerapplication.R.id.sagar)

        // Get layout params for the buttons
        val ashimParams = ashimButton.layoutParams as RelativeLayout.LayoutParams
        // Change layout params properties
        ashimParams.width = 500 // Change width
        ashimParams.height = 150 // Change height
        ashimParams.setMargins(0, 20, 0, 0) // Change margins (top margin)
        ashimButton.textSize = 18F // Change text size
        ashimButton.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark)) // Change text color
        ashimButton.updatePadding(10, 10, 10, 10) // Change padding
        ashimParams.addRule(RelativeLayout.BELOW, com.example.recyclerapplication.R.id.shop) // Change relative positioning (below shopButton)
        ashimParams.addRule(RelativeLayout.ALIGN_PARENT_START) // Align to the start of the parent
        ashimParams.addRule(RelativeLayout.CENTER_HORIZONTAL) // Center horizontally
//        ashimParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM) // Align to the bottom of the parent

        // Apply changes to layout params
        ashimButton.layoutParams = ashimParams


        val shopParams = shopButton.layoutParams as RelativeLayout.LayoutParams
        // Change layout params properties
        shopButton.width = 500 // Change width
        shopButton.height = 150 // Change height
        shopParams.setMargins(0, 20, 0, 0) // Change margins (top margin)
        shopButton.textSize = 18F // Change text size
        shopButton.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark)) // Change text color
        shopButton.updatePadding(10, 10, 10, 10) // Change padding
//        shopParams.addRule(RelativeLayout.BELOW, com.example.recyclerapplication.R.id.shop) // Change relative positioning (below shopButton)
        shopParams.addRule(RelativeLayout.ALIGN_PARENT_START) // Align to the start of the parent
        shopParams.addRule(RelativeLayout.CENTER_HORIZONTAL) // Center horizontally
//        ashimParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM) // Align to the bottom of the parent

        // Apply changes to layout params
        shopButton.layoutParams = shopParams

//        Linear Layout Params


//        val shopButton: Button = findViewById(R.id.shop)
//        val ashimButton: Button = findViewById(R.id.ashim)
//        val nigamButton: Button = findViewById(R.id.nigam)
//        val sagarButton: Button = findViewById(R.id.sagar)
//
//        // Change layout parameters for all buttons
//        val buttonList = listOf(shopButton, ashimButton, nigamButton, sagarButton)
//
//        buttonList.forEach { button ->
//            val layoutParams = button.layoutParams as ViewGroup.LayoutParams
//            layoutParams.width = 200 // Change width
//            layoutParams.height = 100 // Change height
//            button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light)) // Change background color
//            button.textSize = 18F // Change text size
//            button.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark)) // Change text color
//            button.updatePadding(10, 10, 10, 10) // Change padding
//
//            // Apply changes to layout params
//            button.layoutParams = layoutParams
    }
}


