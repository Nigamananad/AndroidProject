package com.example.a3dgame.classes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.sqrt

class JoystickView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val outerCirclePaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL
    }

    private val innerCirclePaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private var centerX = 0f
    private var centerY = 0f
    private var baseRadius = 150f
    private var thumbX = 0f
    private var thumbY = 0f
    private var thumbRadius = 50f

    var joystickListener: ((Float, Float) -> Unit)? = null  // Callback for movement

    init {
        thumbX = centerX
        thumbY = centerY
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Outer circle (base)
        canvas.drawCircle(centerX, centerY, baseRadius, outerCirclePaint)

        // Inner circle (thumb)
        canvas.drawCircle(thumbX, thumbY, thumbRadius, innerCirclePaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width * 0.5f
        centerY = height * 0.5f
        thumbX = centerX
        thumbY = centerY
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val dx = event.x - centerX
                val dy = event.y - centerY
                val distance = sqrt(dx * dx + dy * dy)

                if (distance < baseRadius) {
                    thumbX = event.x
                    thumbY = event.y
                } else {
                    val ratio = baseRadius / distance
                    thumbX = centerX + dx * ratio
                    thumbY = centerY + dy * ratio
                }

                val normalizedX = (thumbX - centerX) / baseRadius
                val normalizedY = (thumbY - centerY) / baseRadius
                joystickListener?.invoke(normalizedX, normalizedY)

                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                thumbX = centerX
                thumbY = centerY
                joystickListener?.invoke(0f, 0f)
                invalidate()
            }
        }
        return true
    }
}

