package com.example.googleaddemo

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {

        }
        //with AdView
        loadBannerAd()

        //with Fragment without AdView
        loadAdaptiveBanner()
    }

    //    private fun getAdSize(): AdSize {
//        val display = windowManager.defaultDisplay
//        val outMetrics = DisplayMetrics()
//        display.getMetrics(outMetrics)
//
//        val density = outMetrics.density
//        var adWidthPixels = outMetrics.widthPixels.toFloat()
//        val adWidth = (adWidthPixels / density).toInt()
//
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
//    }
    private fun loadBannerAd() {
        val adView = findViewById<AdView>(R.id.adView)
//        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test ID
//        adView.setAdSize(getAdSize())
        val extras = Bundle()
        extras.putString("collapsible", "bottom")

        val adRequest = AdRequest.Builder()
            .addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
            .build()
        adView.loadAd(adRequest)
    }

    private fun loadAdaptiveBanner() {
        val adContainer = findViewById<FrameLayout>(R.id.ad_container)
        val adView = AdView(this).apply {
            adUnitId = "ca-app-pub-3940256099942544/6300978111"
//            adSize = getAdSize()
        }
        adView.setAdSize(getAdSize1())
        adContainer.removeAllViews()
        adContainer.addView(adView)
        adView.loadAd(AdRequest.Builder().build())
    }

    private fun getAdSize1(): AdSize {
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density
        var adWidthPixels = outMetrics.widthPixels.toFloat()
        val adWidth = (adWidthPixels / density).toInt()

        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
    }
}