package com.jndevelopstudio.nonetexplore.activity.tictactoe

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.other_class.BaseActivity
import java.util.Random
import java.util.Timer
import java.util.TimerTask

class TicTacActivity : BaseActivity() {
    private var easyMode = true
    private var mediumMode: Boolean = false
    private var hardMode: Boolean = false
    private var impossibleMode: Boolean = false
    private var player1SelectIcon: Boolean = false
    val r = Random()
    var flag = 0
    private var ax = 10
    private var zero = 1
    private var win = 0
    private var game = 1
    private var summ = 0
    private var ctrflag = 0
    private var resetchecker = 1
    private var currentgamedonechecker = 0
    private var score1 = 0
    private var score2 = 0
    private var drawchecker = 0
    private var tracker = Array(3) { IntArray(3) } // 2D array initialized to 0
    private var sum = IntArray(8) // 1D array of size 8 initialized to 0
    private var buttonpressed = Array(3) { IntArray(3) } // 2D array initialized to 0
    private var selectedSinglePlayer: Boolean = false
    private var savedValue: Int = 0
    private var player1: CharSequence = "Player 1"
    private var player2: CharSequence = "Player 2"
    private var vibration: Boolean = false
    private lateinit var fx1: ImageView
    private lateinit var fx2: ImageView
    private lateinit var fx3: ImageView
    private lateinit var fx4: ImageView
    private lateinit var fx5: ImageView
    private lateinit var fx6: ImageView
    private lateinit var fx7: ImageView
    private lateinit var fx8: ImageView
    private lateinit var fx9: ImageView
    private lateinit var rootLayout: View

    companion object {
        private const val PREFS_NAME = "vibration"
        private const val PREF_VIBRATION = "TicVib"
        const val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
        const val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
    }

    var revealX: Int = 0
    var revealY: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac)
        handleNotchScreen()
        fx1 = findViewById(R.id.firstBox)
        fx2 = findViewById(R.id.secondBox)
        fx3 = findViewById(R.id.thirdBox)
        fx4 = findViewById(R.id.fourBox)
        fx5 = findViewById(R.id.fiveBox)
        fx6 = findViewById(R.id.sixBox)
        fx7 = findViewById(R.id.sevenBox)
        fx8 = findViewById(R.id.eightBox)
        fx9 = findViewById(R.id.nineBox)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                vibration = preferences.getBoolean(PREF_VIBRATION, true)
                val sharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(applicationContext)
                savedValue = sharedPreferences.getInt("key", 0)
            }
        }, 0, 2) // Put time here, 1000 milliseconds = 1 second

        val w = window // In Activity's onCreate(), for instance
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val players = intent.getCharSequenceArrayExtra("playersnames")
        player1 = players?.get(0) ?: "Player 1"
        player2 = players?.get(1) ?: "Player 2"

        player1SelectIcon = intent.getBooleanExtra("player1ax", true)
        selectedSinglePlayer = intent.getBooleanExtra("selectedsingleplayer", true)

        if (player1SelectIcon) {
            ax = 1
            zero = 10
        }

        val textName = findViewById<TextView>(R.id.firstName)
        val textName2 = findViewById<TextView>(R.id.secondName)
        textName.text = player1
        if (!selectedSinglePlayer) {
            textName2.text = player2
            Toast.makeText(this, "$player1's turn", Toast.LENGTH_SHORT).show()
        }
        manageClick()
        rootLayout = findViewById(R.id.main)
        if (savedInstanceState == null &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)
        ) {
            rootLayout.visibility = View.INVISIBLE
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)
            val viewTreeObserver = rootLayout.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object :
                    ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(revealX, revealY)
                        rootLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
        } else {
            rootLayout.visibility = View.VISIBLE
        }
    }

    private fun revealActivity(x: Int, y: Int) {
        val finalRadius = (Math.max(rootLayout.width, rootLayout.height) * 1.1).toFloat()
        // Create the animator for this view (the start radius is zero)
        val circularReveal =
            ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0f, finalRadius)
        circularReveal.duration = 400
        circularReveal.interpolator = AccelerateInterpolator()
        // Make the view visible and start the animation
        rootLayout.visibility = View.VISIBLE
        circularReveal.start()
    }

    private fun unRevealActivity() {
        val finalRadius = (Math.max(rootLayout.width, rootLayout.height) * 1.1).toFloat()
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            rootLayout, revealX, revealY, finalRadius, 0f
        )
        circularReveal.duration = 400
        circularReveal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                rootLayout.visibility = View.INVISIBLE
                // Finish the activity
                finish()
            }
        })
        circularReveal.start()
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        unRevealActivity()
    }

    private fun manageClick() {
        fx1.setOnClickListener {
            firstBox()
        }
        fx2.setOnClickListener {
            secondBox()
        }
        fx3.setOnClickListener {
            thirdBox()
        }
        fx4.setOnClickListener {
            fourBox()
        }
        fx5.setOnClickListener {
            fiveBox()
        }
        fx6.setOnClickListener {
            sixBox()
        }
        fx7.setOnClickListener {
            sevenBox()
        }
        fx8.setOnClickListener {
            eightBox()
        }
        fx9.setOnClickListener {
            nineBox()
        }
    }


    private fun firstBox() {
        if (win == 0 && buttonpressed[0][0] == 0) {
            tracker[0][0] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            flag++
            buttonpressed[0][0]++
        }
    }

    private fun secondBox() {
        if (win == 0 && buttonpressed[0][1] == 0) {
            tracker[0][1] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            buttonpressed[0][1]++
            flag++
        }
    }

    private fun thirdBox() {
        if (win == 0 && buttonpressed[0][2] == 0) {
            tracker[0][2] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            buttonpressed[0][2]++
            flag++
        }
    }

    private fun fourBox() {
        if (win == 0 && buttonpressed[1][0] == 0) {
            tracker[1][0] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            buttonpressed[1][0]++
            flag++
        }
    }

    private fun fiveBox() {
        if (win == 0 && buttonpressed[1][1] == 0) {
            tracker[1][1] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            buttonpressed[1][1]++
            flag++
        }
    }

    private fun sixBox() {
        if (win == 0 && buttonpressed[1][2] == 0) {
            tracker[1][2] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            buttonpressed[1][2]++
            flag++
        }
    }

    private fun sevenBox() {
        if (win == 0 && buttonpressed[2][0] == 0) {
            tracker[2][0] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            buttonpressed[2][0]++
            flag++
        }
    }

    private fun eightBox() {
        if (win == 0 && buttonpressed[2][1] == 0) {
            tracker[2][1] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            buttonpressed[2][1]++
            flag++
        }
    }

    private fun nineBox() {
        if (win == 0 && buttonpressed[2][2] == 0) {
            tracker[2][2] = if (flag % 2 == 0) ax else zero
            printBoard()
            winnerCheck()
            aiPlayer()
            buttonpressed[2][2]++
            flag++
        }
    }


    private fun aiPlayer() {
        if (selectedSinglePlayer && win == 0) {
            val handler = Handler(Looper.getMainLooper())
            val t = Timer()
            t.schedule(object : TimerTask() {
                override fun run() {
                    handler.post {
                        // Add code to be executed after a pause
                        if (ifCpuWin())
                        else if (ifOpoWin())
                        else if (emptyCentre())
                        else if (emptyCorner())
                        else emptyAny()
                        printBoard()
                        winnerCheck()
                        flag++
                    }
                }
            }, 110)
        }
    }


    fun ifCpuWin(): Boolean {
        if (!easyMode) {
            for (i in 0..7) {
                if (sum[i] == 2 * zero) {
                    when (i) {
                        0 -> {
                            for (x in 0..2) {
                                if (tracker[0][x] == 0) {
                                    tracker[0][x] = zero
                                }
                            }
                        }

                        1 -> {
                            for (x in 0..2) {
                                if (tracker[1][x] == 0) {
                                    tracker[1][x] = zero
                                }
                            }
                        }

                        2 -> {
                            for (x in 0..2) {
                                if (tracker[2][x] == 0) {
                                    tracker[2][x] = zero
                                }
                            }
                        }

                        3 -> {
                            for (x in 0..2) {
                                if (tracker[x][0] == 0) {
                                    tracker[x][0] = zero
                                }
                            }
                        }

                        4 -> {
                            for (x in 0..2) {
                                if (tracker[x][1] == 0) {
                                    tracker[x][1] = zero
                                }
                            }
                        }

                        5 -> {
                            for (x in 0..2) {
                                if (tracker[x][2] == 0) {
                                    tracker[x][2] = zero
                                }
                            }
                        }

                        6 -> {
                            for (y in 0..2) {
                                for (x in 0..2) {
                                    if (x == y && tracker[x][y] == 0) {
                                        tracker[x][y] = zero
                                    }
                                }
                            }
                        }

                        7 -> {
                            when {
                                tracker[0][2] == 0 -> tracker[0][2] = zero
                                tracker[1][1] == 0 -> tracker[1][1] = zero
                                else -> tracker[2][0] = zero
                            }
                        }
                    }
                    return true
                }
            }
        }
        return false
    }

    fun ifOpoWin(): Boolean {
        if (!easyMode || !mediumMode) {
            for (i in 0..7) {
                if (sum[i] == 2 * ax) {
                    when (i) {
                        0 -> {
                            for (x in 0..2) {
                                if (tracker[0][x] == 0) {
                                    tracker[0][x] = zero
                                    buttonpressed[0][x]++
                                }
                            }
                        }

                        1 -> {
                            for (x in 0..2) {
                                if (tracker[1][x] == 0) {
                                    tracker[1][x] = zero
                                    buttonpressed[1][x]++
                                }
                            }
                        }

                        2 -> {
                            for (x in 0..2) {
                                if (tracker[2][x] == 0) {
                                    tracker[2][x] = zero
                                    buttonpressed[2][x]++
                                }
                            }
                        }

                        3 -> {
                            for (x in 0..2) {
                                if (tracker[x][0] == 0) {
                                    tracker[x][0] = zero
                                    buttonpressed[x][0]++
                                }
                            }
                        }

                        4 -> {
                            for (x in 0..2) {
                                if (tracker[x][1] == 0) {
                                    tracker[x][1] = zero
                                    buttonpressed[x][1]++
                                }
                            }
                        }

                        5 -> {
                            for (x in 0..2) {
                                if (tracker[x][2] == 0) {
                                    tracker[x][2] = zero
                                    buttonpressed[x][2]++
                                }
                            }
                        }

                        6 -> {
                            for (y in 0..2) {
                                for (x in 0..2) {
                                    if (x == y && tracker[x][y] == 0) {
                                        tracker[x][y] = zero
                                        buttonpressed[x][y]++
                                    }
                                }
                            }
                        }

                        7 -> {
                            when {
                                tracker[0][2] == 0 -> {
                                    tracker[0][2] = zero
                                    buttonpressed[0][2]++
                                }

                                tracker[1][1] == 0 -> {
                                    tracker[1][1] = zero
                                    buttonpressed[1][1]++
                                }

                                else -> {
                                    tracker[2][0] = zero
                                    buttonpressed[2][0]++
                                }
                            }
                        }
                    }
                    return true
                }
            }
        }
        return false
    }

    fun emptyCentre(): Boolean {
        if (impossibleMode || hardMode) {
            if (tracker[1][1] == 0) {
                tracker[1][1] = zero
                buttonpressed[1][1]++
                return true
            }
        }
        return false
    }

    fun emptyCorner(): Boolean {
        if (hardMode || impossibleMode) {
            if ((tracker[0][0] + tracker[2][2] == 2 * ax) || (tracker[0][2] + tracker[2][0] == 2 * ax)) {
                for (k in 0..2) {
                    for (j in 0..2) {
                        if ((k + j) % 2 == 1) {
                            if (tracker[k][j] == 0) {
                                tracker[k][j] = zero
                            }
                            buttonpressed[k][j]++
                            return true
                        }
                    }
                }
            }
        }

        if (impossibleMode) {
            if (sum[6] == zero || sum[7] == zero) {
                if (sum[6] == zero) {
                    if ((sum[0] + sum[3]) > (sum[2] + sum[5])) {
                        tracker[0][0] = zero
                        buttonpressed[0][0]++
                    } else {
                        tracker[2][2] = zero
                        buttonpressed[2][2]++
                    }
                    return true
                }

                if (sum[7] == zero) {
                    if ((sum[0] + sum[5]) > (sum[3] + sum[2])) {
                        tracker[0][2] = zero
                        buttonpressed[0][2]++
                    } else {
                        tracker[2][0] = zero
                        buttonpressed[2][0]++
                    }
                    return true
                }
            }
        }

        for (i in 0..2) {
            if (tracker[0][i] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero
                    buttonpressed[0][0]++
                    return true
                }
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero
                    buttonpressed[0][2]++
                    return true
                }
            }
        }

        for (i in 0..2) {
            if (tracker[2][i] == ax) {
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero
                    buttonpressed[2][0]++
                    return true
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero
                    buttonpressed[2][2]++
                    return true
                }
            }
        }

        for (i in 0..2) {
            if (tracker[i][0] == ax) {
                if (tracker[0][0] == 0) {
                    tracker[0][0] = zero
                    buttonpressed[0][0]++
                    return true
                }
                if (tracker[2][0] == 0) {
                    tracker[2][0] = zero
                    buttonpressed[2][0]++
                    return true
                }
            }
        }

        for (i in 0..2) {
            if (tracker[i][2] == ax) {
                if (tracker[0][2] == 0) {
                    tracker[0][2] = zero
                    buttonpressed[0][2]++
                    return true
                }
                if (tracker[2][2] == 0) {
                    tracker[2][2] = zero
                    buttonpressed[2][2]++
                    return true
                }
            }
        }

        return false
    }

    fun emptyAny() {
        if (ctrflag == 0) {
            while (true) {
                val x = rand()
                val y = rand()
                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero
                    buttonpressed[x][y]++
                    return
                }
            }
        }
        for (x in 0..2) {
            for (y in 0..2) {
                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero
                    buttonpressed[x][y]++
                    return
                }
            }
        }
    }

    private fun rand(): Int {
        return r.nextInt(3)
    }

    fun printBoard() {
        if (tracker[0][0] == 1) fx1.setImageResource(R.drawable.crossfx)
        if (tracker[0][0] == 10) fx1.setImageResource(R.drawable.zerofx)
        if (tracker[0][1] == 1) fx2.setImageResource(R.drawable.crossfx)
        if (tracker[0][1] == 10) fx2.setImageResource(R.drawable.zerofx)
        if (tracker[0][2] == 1) fx3.setImageResource(R.drawable.crossfx)
        if (tracker[0][2] == 10) fx3.setImageResource(R.drawable.zerofx)
        if (tracker[1][0] == 1) fx4.setImageResource(R.drawable.crossfx)
        if (tracker[1][0] == 10) fx4.setImageResource(R.drawable.zerofx)
        if (tracker[1][1] == 1) fx5.setImageResource(R.drawable.crossfx)
        if (tracker[1][1] == 10) fx5.setImageResource(R.drawable.zerofx)
        if (tracker[1][2] == 1) fx6.setImageResource(R.drawable.crossfx)
        if (tracker[1][2] == 10) fx6.setImageResource(R.drawable.zerofx)
        if (tracker[2][0] == 1) fx7.setImageResource(R.drawable.crossfx)
        if (tracker[2][0] == 10) fx7.setImageResource(R.drawable.zerofx)
        if (tracker[2][1] == 1) fx8.setImageResource(R.drawable.crossfx)
        if (tracker[2][1] == 10) fx8.setImageResource(R.drawable.zerofx)
        if (tracker[2][2] == 1) fx9.setImageResource(R.drawable.crossfx)
        if (tracker[2][2] == 10) fx9.setImageResource(R.drawable.zerofx)
        resetchecker++
    }

    fun winnerCheck() {
        ctrflag++
        sum[0] = tracker[0][0] + tracker[0][1] + tracker[0][2]
        sum[1] = tracker[1][0] + tracker[1][1] + tracker[1][2]
        sum[2] = tracker[2][0] + tracker[2][1] + tracker[2][2]
        sum[3] = tracker[0][0] + tracker[1][0] + tracker[2][0]
        sum[4] = tracker[0][1] + tracker[1][1] + tracker[2][1]
        sum[5] = tracker[0][2] + tracker[1][2] + tracker[2][2]
        sum[6] = tracker[0][0] + tracker[1][1] + tracker[2][2]
        sum[7] = tracker[0][2] + tracker[1][1] + tracker[2][0]
        currentgamedonechecker++
        resetchecker++
        for (i in 0..7) {
            if (sum[i] == 3 || sum[i] == 30) {
                if (vibration) {
                    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(400)
                }
                win++
                when {
                    sum[i] == 3 && ax == 1 -> handleWin(R.id.score1, ++score1, player1.toString())
                    sum[i] == 3 && zero == 1 -> handleWin(R.id.score2, ++score2, "AI")
                    sum[i] == 30 && ax == 10 -> handleWin(R.id.score1, ++score1, player1.toString())
                    sum[i] == 30 && zero == 10 -> handleWin(R.id.score2, ++score2, "AI")
                }
            }
        }
        if (ctrflag == 9 && win == 0) {
            Toast.makeText(applicationContext, "DRAW!", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({ playMore() }, 900)
            drawchecker++
        }
    }

    private fun handleWin(scoreTextId: Int, updatedScore: Int, winner: String) {
        val scoreTextView: TextView = findViewById(scoreTextId)
        scoreTextView.setTextColor(getColor(R.color.contextRed))
        Handler().postDelayed({
            scoreTextView.setTextColor(getColor(R.color.context))
        }, 300)
        scoreTextView.text = "$updatedScore"
        Handler(Looper.getMainLooper()).postDelayed({
            playMore()
        }, 500)
        // Uncomment if you want to show a dialog or Toast for winning
        // Toast.makeText(applicationContext, "$winner won!", Toast.LENGTH_SHORT).show()
    }

    private fun playMore() {
        val max = 4
        val min = 1
        when (savedValue) {
            0, 5 -> {
                val random = (min..max).random()
                easyMode = random == 1
                mediumMode = random == 2
                hardMode = random == 3
                impossibleMode = random == 4
            }

            1 -> {
                easyMode = true
                mediumMode = false
                hardMode = false
                impossibleMode = false
            }

            2 -> {
                easyMode = false
                mediumMode = true
                hardMode = false
                impossibleMode = false
            }

            3 -> {
                easyMode = false
                mediumMode = false
                hardMode = true
                impossibleMode = false
            }

            4 -> {
                easyMode = false
                mediumMode = false
                hardMode = false
                impossibleMode = true
            }
        }
        if (drawchecker > 0 || win > 0) {
            game++
            for (i in 0..7) {
                sum[i] = 0
            }
            drawchecker = 0
            // Reset game board UI
            val boardViews = listOf(
                R.id.firstBox, R.id.secondBox, R.id.thirdBox,
                R.id.fourBox, R.id.fiveBox, R.id.sixBox,
                R.id.sevenBox, R.id.eightBox, R.id.nineBox
            )
            boardViews.forEach { id ->
                findViewById<ImageView>(id).setImageDrawable(null)
            }
            // Reset tracker and button pressed states
            for (i in 0..2) {
                for (j in 0..2) {
                    buttonpressed[i][j] = 0
                    tracker[i][j] = 0
                }
            }
            // Display turn information in multiplayer
            if (!selectedSinglePlayer) {
                val turnPlayer = if ((game + 1) % 2 == 0) player1 else player2
                Toast.makeText(this, "$turnPlayer's turn", Toast.LENGTH_SHORT).show()
            }
            // Reset game state
            win = 0
            summ = 0
            ctrflag = 0
            flag = (game + 1) % 2
            currentgamedonechecker = 0
            // CPU plays if in single-player mode and it's its turn
            if (selectedSinglePlayer && game % 2 == 0) {
                aiPlayer()
            }
        }
    }

    fun doReset() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                tracker[i][j] = 0
                buttonpressed[i][j] = 0
            }
        }
        fx1.setImageDrawable(null)
        fx2.setImageDrawable(null)
        fx3.setImageDrawable(null)
        fx4.setImageDrawable(null)
        fx5.setImageDrawable(null)
        fx6.setImageDrawable(null)
        fx7.setImageDrawable(null)
        fx8.setImageDrawable(null)
        fx9.setImageDrawable(null)
        win = 0
        drawchecker = 0
        summ = 0
        resetchecker = 0
        ctrflag = 0
        score1 = 0
        score2 = 0
        game = 1
        flag = 0
        currentgamedonechecker = 0
        val qqq = findViewById<TextView>(R.id.score1)
        qqq.text = score1.toString()
        val qqqq = findViewById<TextView>(R.id.score2)
        qqqq.text = score2.toString()
        Toast.makeText(this, "$player1's turn", Toast.LENGTH_SHORT).show()
    }
}