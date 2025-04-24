package com.example.a3dgame.classes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Snake {
    var segments = mutableListOf<Pair<Float, Float>>()
    var directionX = 1f  // Default movement (right)
    var directionY = 0f
    var speed = 5f

    init {
        // Initial snake body
        segments.add(Pair(500f, 800f))
        segments.add(Pair(480f, 800f))
        segments.add(Pair(460f, 800f))
    }

    fun update() {
        for (i in segments.size - 1 downTo 1) {
            segments[i] = segments[i - 1]
        }

        val head = segments[0]
        segments[0] = Pair(head.first + directionX * speed, head.second + directionY * speed)
    }

    fun grow() {
        val lastSegment = segments.last()
        segments.add(Pair(lastSegment.first, lastSegment.second))
    }

    fun setDirection(dx: Float, dy: Float) {
        if (dx != 0f || dy != 0f) {
            directionX = dx
            directionY = dy
        }
    }

    fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.GREEN
        for (segment in segments) {
            canvas.drawRect(
                segment.first, segment.second,
                segment.first + 20, segment.second + 20, paint
            )
        }
    }
}
