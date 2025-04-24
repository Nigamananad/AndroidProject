package com.jndevelopstudio.nonetexplore.activity.tictactoe

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.other_class.BaseActivity

class ChooseActivity : BaseActivity() {
    private lateinit var btnWithFriend: CardView
    private lateinit var btnWithAI: CardView
    private var isAICheck = false
    private var isZeroCheck = false
    private var lastClickTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        handleNotchScreen()
        btnWithFriend = findViewById(R.id.btnWithFriend)
        btnWithAI = findViewById(R.id.btnWithAI)
        manageClick()
    }


    private fun showAlertDialogButtonClicked() {
        // Create an alert builder
        val builder = AlertDialog.Builder(this)
        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.choose_dialog, null)
        builder.setView(customLayout)
        val imageZero = customLayout.findViewById<ImageView>(R.id.imageViewZeroFx)
        val imageCross = customLayout.findViewById<ImageView>(R.id.imageViewCrossFx)
        val btnNextStep = customLayout.findViewById<AppCompatButton>(R.id.btnNextStep)
        val textViewTitle = customLayout.findViewById<TextView>(R.id.textViewTitle)
        val btnClose=customLayout.findViewById<ImageView>(R.id.ivCloseBtn)

        val dialog = builder.create()
        dialog.setCancelable(false)

        textViewTitle.text = if (!isAICheck) {
            "Choose Player 1"
        } else {
            "Your Choice"
        }

        imageZero.setOnClickListener {
            isZeroCheck = true
            imageZero.alpha = 1f
            imageCross.alpha = 0.4f
        }

        imageCross.setOnClickListener {
            isZeroCheck = false
            imageCross.alpha = 1f
            imageZero.alpha = 0.4f
        }

        btnNextStep.setOnClickListener {
            showAlertDialogNameEnter()
            dialog.dismiss()
        }
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        // create and show the alert dialog
        dialog.show()
    }


    private fun showAlertDialogNameEnter() {
        // Create an alert builder
        val builder = AlertDialog.Builder(this)
        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.name_dialog, null)
        builder.setView(customLayout)
        val player1Name = customLayout.findViewById<EditText>(R.id.etPlayer1)
        val player2Name = customLayout.findViewById<EditText>(R.id.etPlayer2)
        val btnStartGame = customLayout.findViewById<AppCompatButton>(R.id.btnStartGame)
        player1Name.hint = if (!isAICheck) "Player 1 Name" else "Enter Name"
        player2Name.visibility = if (!isAICheck) View.VISIBLE else View.GONE

        btnStartGame.setOnClickListener {
            val pl = player1Name.text.toString().trim()
            val p2 = player2Name.text.toString().trim()
            startActivity(Intent(this, TicTacActivity::class.java).apply {
                val array = arrayOf(pl, p2)
                putExtra("playersnames", array)
                putExtra("player1ax", isZeroCheck)
                putExtra("selectedsingleplayer", isAICheck)
            })
            finish()
        }

        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

    private fun presentActivity(view: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "transition")
        val revealX = (view.x + view.width / 2).toInt()
        val revealY = (view.y + view.height / 2).toInt()
        val intent = Intent(this, TicTacActivity::class.java).apply {
            putExtra(TicTacActivity.EXTRA_CIRCULAR_REVEAL_X, revealX)
            putExtra(TicTacActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY)
//            putExtra()
        }
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

    private fun manageClick() {
        btnWithFriend.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime < 500) {
                return@setOnClickListener
            }
            lastClickTime = SystemClock.elapsedRealtime()
            isAICheck = false
            showAlertDialogButtonClicked()
        }

        btnWithAI.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime < 500) {
                return@setOnClickListener
            }
            lastClickTime = SystemClock.elapsedRealtime()
            isAICheck = true
            showAlertDialogButtonClicked()
        }
    }
}