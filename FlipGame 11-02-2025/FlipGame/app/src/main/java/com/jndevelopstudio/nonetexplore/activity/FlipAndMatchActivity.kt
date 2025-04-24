package com.jndevelopstudio.nonetexplore.activity

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.adapter.CardAdapter
import com.jndevelopstudio.nonetexplore.other_class.BaseActivity
import com.jndevelopstudio.nonetexplore.view.CircularProgressBar

class FlipAndMatchActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter
    private var currentLevel = 1
    private lateinit var progressBar: CircularProgressBar
    private var timerDuration = 5 // Initial timer duration in seconds
    private var progressAnimator: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_and_match)
        handleNotchScreen()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.circularProgressBar)

        recyclerView.layoutManager = GridLayoutManager(this, 4) // 4 cards in a row
        loadLevel(currentLevel)

        // Set the images (you can load different images for match game)
    }

    private fun loadLevel(level: Int) {
        val cardImages = listOf(
            R.drawable.pet1,
            R.drawable.pet2,
            R.drawable.pet3,
            R.drawable.pet4,
            R.drawable.pet5,
            R.drawable.pet6,
            R.drawable.pet7,
            R.drawable.pet8
        )

        val imagesForLevel = cardImages.take(level + 1) // Taking (level + 1) images
        val duplicatedImages = imagesForLevel.flatMap { listOf(it, it) }
        val shuffledImages = duplicatedImages.shuffled()

        cardAdapter = CardAdapter(shuffledImages, true, recyclerView) {
            showCongratulatoryDialog()
        }
        recyclerView.adapter = cardAdapter

        // Flip all cards back after 5 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            cardAdapter.flipAllCards()
            startProgressBarTimer()
        }, 1500)
    }

    private fun startProgressBarTimer(remainingTime: Int = timerDuration) {
        // Stop any ongoing animation
        stopProgressBarTimer()

        // Create and start a new animator
        progressAnimator = ValueAnimator.ofInt(remainingTime, 0).apply {
            duration = remainingTime * 1000L
            addUpdateListener { animation ->
                val timeLeft = animation.animatedValue as Int
                progressBar.setProgress((timeLeft * 100) / remainingTime, timeLeft)
            }
            doOnEnd {
                // Timer finished, check if game is complete
                Log.d("TAG787878", "startProgressBarTimer 1: ${cardAdapter.matchedPairs} ")
                Log.d("TAG787878", "startProgressBarTimer 2: ${cardAdapter.itemCount / 2} ")

                if (cardAdapter.matchedPairs < cardAdapter.itemCount / 2) {
                    // Show loose dialog if all cards are not matched
                    showLooseDialog()
                }
            }
            start()
        }
    }

    private fun showLooseDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_loose_game_dialog, null)
        val btnRetry = dialogView.findViewById<LinearLayout>(R.id.btn_retry)
        val btnAddTime = dialogView.findViewById<LinearLayout>(R.id.btn_watch_ad)

        val dialog = AlertDialog.Builder(this, R.style.TransparentDialog)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnRetry.setOnClickListener {
            dialog.dismiss()
            loadLevel(currentLevel)
        }
        btnAddTime.setOnClickListener {
            dialog.dismiss() // Dismiss the dialog

            // Resume the timer from where it left off with added time
            progressAnimator?.let { animator ->
                val remainingTime = animator.animatedValue as Int // Current remaining time
                startProgressBarTimer(remainingTime + 30) // Add 30 seconds and restart the timer
            }
        }
        dialog.show()
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        pauseProgressBarTimer()
        showExitDialog { isCancel ->
            if (isCancel) {
                startProgressBarTimer(progressAnimator?.animatedValue.toString().toInt())
            } else {
                progressAnimator = null
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun stopProgressBarTimer() {
        progressAnimator?.pause() // Cancel the animation
        progressAnimator = null
    }

    private fun pauseProgressBarTimer() {
        progressAnimator?.pause() // Cancel the animation
    }

    @SuppressLint("SetTextI18n")
    private fun showCongratulatoryDialog() {
        stopProgressBarTimer()

        val dialogView = layoutInflater.inflate(R.layout.custom_congratulations_dialog, null)
        // Find views in the dialog layout
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialogMessage)
        val nextLevelButton = dialogView.findViewById<Button>(R.id.btnNextLevel)

        val dialog = AlertDialog.Builder(this, R.style.TransparentDialog)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialogMessage.text = "You’ve completed the level $currentLevel"

        // Set button click listener
        nextLevelButton.setOnClickListener {
            dialog.dismiss() // Close the dialog
            currentLevel++
            timerDuration += 5 // Increase timer duration for next level
            loadLevel(currentLevel) // Load the next level
        }

        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressAnimator = null
    }
}