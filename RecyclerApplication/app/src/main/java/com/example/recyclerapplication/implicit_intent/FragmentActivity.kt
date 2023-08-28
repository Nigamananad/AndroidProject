package com.example.recyclerapplication.implicit_intent

import FirstFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerapplication.R

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FirstFragment())
                .commit()
        }
    }
}