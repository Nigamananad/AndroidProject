package com.jndevelopstudio.nonetexplore.activity.dodgemaster

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import androidx.core.content.ContextCompat
import com.jndevelopstudio.nonetexplore.R
import com.sanchayastudio.savethebunny.GameView
import java.util.Random

class Spike(context: Context) {
    private val spike = arrayOfNulls<Bitmap>(3)
    var spikeFrame = 0
    var spikeX = 0
    var spikeY = 0
    var spikeVelocity = 0
    private val random = Random()

    init {
        spike[0] = vectorDrawableToBitmap(context, R.drawable.wepo)
        spike[1] = vectorDrawableToBitmap(context, R.drawable.wepo)
        spike[2] = vectorDrawableToBitmap(context, R.drawable.wepo)
        resetPosition()
    }

    fun getSpike(spikeFrame: Int): Bitmap {
        return spike.getOrNull(spikeFrame)!!
    }

    fun getSpikeWidth(): Int {
        return spike[0]?.width ?: 0
    }

    fun getSpikeHeight(): Int {
        return spike[0]?.height ?: 0
    }


    fun resetPosition() {
        spikeX = random.nextInt(GameView.dWidth - getSpikeWidth())
        spikeY = -200 + random.nextInt(600) * -1
        spikeVelocity = 35 + random.nextInt(16)
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
}