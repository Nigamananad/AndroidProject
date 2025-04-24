package com.sanchayastudio.savethebunny

//region BACKUP
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.graphics.Canvas
//import android.graphics.Color
//import android.graphics.Paint
//import android.graphics.Point
//import android.graphics.Rect
//import android.os.Handler
//import android.view.MotionEvent
//import android.view.View
//import java.util.Random
//
//class GameView(context: Context?) : View(context) {
//    var background: Bitmap? = null
//    var ground: Bitmap? = null
//    var rabbit: Bitmap? = null
//    var rectBackground: Rect? = null
//    var rectGround: Rect? = null
//    private var context: Context? = null
//    val UPDATE_MILLIS: Long = 30
//    var runnable: Runnable? = null
//    val textPaint = Paint()
//    val healthPaint = Paint()
//    val TEXT_SIZE = 120f
//    var points = 0
//    var life = 3
//    val random = Random()
//    private var rabbitX = 0f
//    private var rabbitY = 0f
//    var oldX = 0f
//    var oldRabbitX = 0f
//    private var spikes: ArrayList<Spike>? = null
//    private var explosions: ArrayList<Explosion>? = null
//
//    companion object {
//        var dWidth = 0
//        var dHeight = 0
//    }
//
//    init {
//        this.context = context
//        background = BitmapFactory.decodeResource(resources, R.drawable.background)
//        ground = BitmapFactory.decodeResource(resources, R.drawable.ground)
//        rabbit = BitmapFactory.decodeResource(resources, R.drawable.rabbit)
//
//        val display = (context as Activity).windowManager.defaultDisplay
//        val size = Point()
//        display.getSize(size)
//        dWidth = size.x
//        dHeight = size.y
//        rectBackground = Rect(0, 0, dWidth, dHeight)
//        rectGround = Rect(0, dHeight - ground!!.height, dWidth, dHeight)
//        runnable = Runnable { invalidate() }
//
//        textPaint.color = Color.rgb(255, 165, 0)
//        textPaint.textSize = TEXT_SIZE
//        textPaint.textAlign = Paint.Align.LEFT
//        healthPaint.color = Color.GREEN
//        rabbitX = (dWidth / 2 - rabbit!!.width / 2).toFloat()
//        rabbitY = (dHeight - ground!!.height - rabbit!!.height).toFloat()
//        spikes = ArrayList()
//        explosions = ArrayList()
//
//        for (i in 0 until 3) {
//            val spike = Spike(context)
//            spikes!!.add(spike)
//        }
//    }
//
//    @SuppressLint("DrawAllocation")
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        canvas.drawBitmap(background!!, null, rectBackground!!, null)
//        canvas.drawBitmap(ground!!, null, rectGround!!, null)
//        canvas.drawBitmap(rabbit!!, rabbitX, rabbitY, null)
//
//        for (i in 0 until spikes!!.size) {
//            canvas.drawBitmap(
//                spikes!![i].getSpike(spikes!![i].spikeFrame),
//                spikes!![i].spikeX.toFloat(),
//                spikes!![i].spikeY.toFloat(),
//                null
//            )
//            spikes!![i].spikeFrame++
//            if (spikes?.get(i)!!.spikeFrame > 2) {
//                spikes?.get(i)!!.spikeFrame = 0
//            }
//
//            spikes!![i].spikeY += spikes!![i].spikeVelocity
//            if (spikes!![i].spikeY + spikes!![i].getSpikeHeight() >= dHeight - ground!!.height) {
//                points += 10
//                val explosion = Explosion(context!!)
//                explosion.explosionX = spikes!![i].spikeX
//                explosion.explosionY = spikes!![i].spikeY
//                explosions?.add(explosion)
//                spikes!![i].resetPosition()
//            }
//        }
//
//        for (i in 0 until spikes!!.size) {
//            if (spikes!![i].spikeX + spikes!![i].getSpikeWidth() >= rabbitX && spikes!![i].spikeX <= rabbitX + rabbit!!.width && spikes!![i].spikeY + spikes!![i].getSpikeWidth() >= rabbitY && spikes!![i].spikeY + spikes!![i].getSpikeWidth() <= rabbitY + rabbit!!.height) {
//                life--
//                spikes!![i].resetPosition()
//                if (life == 0) {
//                    val intent = Intent(context, GameOverActivity::class.java)
//                    intent.putExtra("POINTS", points)
//                    context!!.startActivity(intent)
//                    (context as Activity).finish()
//                }
//            }
//        }
//
//        for (i in 0 until explosions!!.size) {
//            canvas.drawBitmap(
//                explosions!![i].getExplosion(explosions!![i].explosionFrame),
//                explosions!![i].explosionX.toFloat(),
//                explosions!![i].explosionY.toFloat(),
//                null
//            )
//            explosions!![i].explosionFrame++
//            if (explosions!![i].explosionFrame > 3) {
//                explosions!!.removeAt(i)
//            }
//        }
//
//        if (life == 2) {
//            healthPaint.color = Color.YELLOW
//        } else if (life == 1) {
//            healthPaint.color = Color.RED
//        }
//
//        canvas.drawRect(
//            (dWidth - 200).toFloat(),
//            30F,
//            (dWidth - 200 + 60 * life).toFloat(),
//            80F,
//            healthPaint
//        )
//        canvas.drawText("" + points, 20F, TEXT_SIZE, textPaint)
//        Handler().postDelayed(runnable!!, UPDATE_MILLIS)
//    }
//
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        val touchX = event!!.x
//        val touchY = event.y
//        if (touchY > rabbitY) {
//            val action = event.action
//            if (action == MotionEvent.ACTION_DOWN) {
//                oldX = event.x
//                oldRabbitX = rabbitX
//            }
//
//            if (action == MotionEvent.ACTION_MOVE) {
//                val shift = oldX - touchX
//                val newRabbitX = oldRabbitX - shift
//                if (newRabbitX <= 0)
//                    rabbitX = 0F
//                else if (newRabbitX >= dWidth - rabbit!!.width)
//                    rabbitX = (dWidth - rabbit!!.width).toFloat()
//                else
//                    rabbitX = newRabbitX
//            }
//        }
//        return true
//    }
//}
//endregion

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.VectorDrawable
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.activity.dodgemaster.Explosion
import com.jndevelopstudio.nonetexplore.activity.dodgemaster.GameOverActivity
import com.jndevelopstudio.nonetexplore.activity.dodgemaster.Spike
import java.util.Random

class GameView(context: Context?) : View(context) {
    var background: Bitmap? = null

    //    var ground: Bitmap? = null
    var rabbit: Bitmap? = null
    var rectBackground: Rect? = null
    var rectGround: Rect? = null
    val UPDATE_MILLIS: Long = 30
    var runnable: Runnable? = null
    val textPaint = Paint()
    val healthPaint = Paint()
    val TEXT_SIZE = 120f
    var points = 0
    var life = 3
    val random = Random()
    private var rabbitX = 0f
    private var rabbitY = 0f
    var oldX = 0f
    var oldRabbitX = 0f
    private var spikes: ArrayList<Spike>? = null
    private var explosions: ArrayList<Explosion>? = null
    private var heartBitmap: Bitmap? = null

    companion object {
        var dWidth = 0
        var dHeight = 0
    }

    init {
        heartBitmap = vectorDrawableToBitmap(context!!, R.drawable.ic_heart)
    }

    /**
     * Converts a VectorDrawable resource into a Bitmap.
     */
    private fun vectorDrawableToBitmap(context: Context, resId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, resId)
            ?: throw IllegalArgumentException("Resource not found")
        if (drawable is VectorDrawable) {
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        } else {
            throw IllegalArgumentException("Drawable is not a VectorDrawable")
        }
    }

    init {
        background = BitmapFactory.decodeResource(resources, R.drawable.bg_new)
//        ground = BitmapFactory.decodeResource(resources, R.drawable.ground)
        rabbit = vectorDrawableToBitmap(context as Activity, R.drawable.goat)

        val display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        dWidth = size.x
        dHeight = size.y
        rectBackground = Rect(0, 0, dWidth, dHeight)
//        rectGround = Rect(0, dHeight - ground!!.height, dWidth, dHeight)
        rectGround = Rect(0, dHeight - 280, dWidth, dHeight)
        runnable = Runnable { invalidate() }

        textPaint.color = Color.rgb(255, 165, 0)
        textPaint.textSize = TEXT_SIZE
        textPaint.textAlign = Paint.Align.LEFT
//        healthPaint.color = Color.GREEN
        rabbitX = (dWidth / 2 - rabbit!!.width / 2).toFloat()
//        rabbitY = (dHeight - ground!!.height - rabbit!!.height + 280).toFloat()
        rabbitY = (dHeight - 180 - rabbit!!.height).toFloat()
        spikes = ArrayList()
        explosions = ArrayList()

        for (i in 0 until 3) {
            val spike = Spike(context)
            spikes!!.add(spike)
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(background!!, null, rectBackground!!, null)
//        canvas.drawBitmap(ground!!, null, rectGround!!, null)
        canvas.drawBitmap(rabbit!!, rabbitX, rabbitY, null)

        // Draw the hearts for lives
        val heartSize = 100 // Adjust the size of the heart as needed
        val heartMargin = 20 // Space between hearts
        for (i in 0 until life) {
            val left = dWidth - (heartSize + heartMargin) * (i + 1)
            val top = 30
            canvas.drawBitmap(
                heartBitmap!!,
                null,
                Rect(left, top, left + heartSize, top + heartSize),
                null
            )
        }

        for (i in 0 until spikes!!.size) {
            if (spikes!!.isEmpty()) {
                break // Exit the loop if the spikes list is empty
            }

            val spike = spikes!![i] // Get the current spike
            val spikeFrame = spike.spikeFrame // Get the spike frame

            canvas.drawBitmap(
                spike.getSpike(spikeFrame),
                spike.spikeX.toFloat(),
                spike.spikeY.toFloat(),
                null
            )
            spike.spikeFrame++
            if (spike.spikeFrame > 2) {
                spike.spikeFrame = 0
            }

            spike.spikeY += spike.spikeVelocity
//            if (spike.spikeY + spike.getSpikeHeight() >= dHeight - ground!!.height + 280) {
//                points += 10
//                val explosion = Explosion(context!!)
//                explosion.explosionX = spike.spikeX
//                explosion.explosionY = spike.spikeY
//                explosions?.add(explosion)
//                spike.resetPosition()
//            }
            if (spike.spikeY + spike.getSpikeHeight() >= dHeight - 170) {
                points += 10
                val explosion = Explosion(context!!)
                explosion.explosionX = spike.spikeX
                explosion.explosionY = spike.spikeY
                explosions?.add(explosion)
                spike.resetPosition()
            }
        }

        for (i in 0 until spikes!!.size) {
            if (spikes!!.isEmpty()) {
                break // Exit the loop if the spikes list is empty
            }

            val spike = spikes!![i] // Get the current spike
            if (spike.spikeX + spike.getSpikeWidth() >= rabbitX && spike.spikeX <= rabbitX + rabbit!!.width && spike.spikeY + spike.getSpikeWidth() >= rabbitY && spike.spikeY + spike.getSpikeWidth() <= rabbitY + rabbit!!.height) {
                life--
                spike.resetPosition()
                if (life == 0) {
                    val intent = Intent(context, GameOverActivity::class.java)
                    intent.putExtra("POINTS", points)
                    context!!.startActivity(intent)
                    (context as Activity).finish()
                }
            }
        }

        var i = explosions!!.size - 1 // Start from the last index
        while (i >= 0) {
            canvas.drawBitmap(
                explosions!![i].getExplosion(explosions!![i].explosionFrame),
                explosions!![i].explosionX.toFloat(),
                explosions!![i].explosionY.toFloat(),
                null
            )
            explosions!![i].explosionFrame++
            if (explosions!![i].explosionFrame > 3) {
                explosions!!.removeAt(i)
            }
            i-- // Move to the previous index
        }

//        if (life == 2) {
//            healthPaint.color = Color.YELLOW
//        } else if (life == 1) {
//            healthPaint.color = Color.RED
//        }
//
//        canvas.drawRect(
//            (dWidth - 200).toFloat(),
//            30F,
//            (dWidth - 200 + 60 * life).toFloat(),
//            80F,
//            healthPaint
//        )
        canvas.drawText("" + points, 20F, TEXT_SIZE, textPaint)
        Handler(Looper.getMainLooper()).postDelayed(runnable!!, UPDATE_MILLIS)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event!!.x
        val touchY = event.y
        if (touchY > rabbitY) {
            val action = event.action
            if (action == MotionEvent.ACTION_DOWN) {
                oldX = event.x
                oldRabbitX = rabbitX
            }

            if (action == MotionEvent.ACTION_MOVE) {
                val shift = oldX - touchX
                val newRabbitX = oldRabbitX - shift
                rabbitX = if (newRabbitX <= 0)
                    0F
                else if (newRabbitX >= dWidth - rabbit!!.width)
                    (dWidth - rabbit!!.width).toFloat()
                else
                    newRabbitX
            }
        }
        return true
    }
}
