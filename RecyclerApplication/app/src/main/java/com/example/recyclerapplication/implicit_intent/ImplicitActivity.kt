package com.example.recyclerapplication.implicit_intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import com.example.recyclerapplication.R

class ImplicitActivity : AppCompatActivity() {
    lateinit var buttonShowWebsite: Button
    lateinit var editTextUrl: EditText
    lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)
        buttonShowWebsite = findViewById(R.id.buttonShowWebsite)
        editTextUrl = findViewById(R.id.editTextUrl)
        webView = findViewById(R.id.webView)

        buttonShowWebsite.setOnClickListener {
            val url = editTextUrl.text.toString()
            if (url.isNotEmpty()) {
                showWebsite(url)
            }
        }
    }

    private fun showWebsite(url: String) {
        webView.visibility = View.VISIBLE
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }
}