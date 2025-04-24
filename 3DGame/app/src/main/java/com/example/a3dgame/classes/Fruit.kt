package com.example.a3dgame.classes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.a3dgame.R

class Fruit(context: Context) {
    var x = 300f
    var y = 600f
    private val fruitImages = listOf(
        R.drawable.apple,
        R.drawable.banana, 
        R.drawable.orange,
        R.drawable.cherry,
        R.drawable.grapes,
        R.drawable.mango
    ) // Alag alag fruits

    private var currentBitmap: Bitmap

    init {
        val bitmap = BitmapFactory.decodeResource(context.resources, fruitImages.random())
        currentBitmap = Bitmap.createScaledBitmap(bitmap, 60, 60, false)
    }

    fun draw(canvas: Canvas, paint: Paint) {
        canvas.drawBitmap(currentBitmap, x, y, paint)
    }

    fun randomizePosition(width: Int, height: Int, context: Context) {
        x = (50..width - 50).random().toFloat()
        y = (50..height - 50).random().toFloat()

        // Naya random fruit image set karein
        val bitmap = BitmapFactory.decodeResource(context.resources, fruitImages.random())
        currentBitmap = Bitmap.createScaledBitmap(bitmap, 60, 60, false)
    }
}
