package com.jndevelopstudio.nonetexplore.activity.dodgemaster

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import androidx.core.content.ContextCompat
import com.jndevelopstudio.nonetexplore.R

class Explosion(context: Context) {
    private val explosion = arrayOfNulls<Bitmap>(4)
    var explosionFrame = 0
    var explosionX = 0
    var explosionY = 0

    init {
        explosion[0] = vectorDrawableToBitmap(context, R.drawable.explosion0_svg)
        explosion[1] = vectorDrawableToBitmap(context, R.drawable.explosion1_svg)
        explosion[2] = vectorDrawableToBitmap(context, R.drawable.explosion2_svg)
        explosion[3] = vectorDrawableToBitmap(context, R.drawable.explosion3_svg)
    }

    fun getExplosion(explosionFrame: Int): Bitmap {
        return explosion.getOrNull(explosionFrame)!!
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