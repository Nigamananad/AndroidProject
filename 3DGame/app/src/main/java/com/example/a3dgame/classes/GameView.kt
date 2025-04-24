package com.example.a3dgame.classes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.pow
import kotlin.math.sqrt

class GameView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    val snake = Snake()
    private val fruit = Fruit(context)
    private val paint = Paint()

    // Joystick variables
    private var joystickCenterX = 200f
    private var joystickCenterY = 1000f
    private var joystickRadius = 100f
    private var knobX = joystickCenterX
    private var knobY = joystickCenterY
    private var knobRadius = 40f

    init {
        // Game loop to update continuously
        val thread = Thread {
            while (true) {
                snake.update()
                postInvalidate()
                Thread.sleep(50) // Smooth speed
            }
        }
        thread.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw background
        canvas.drawColor(Color.BLACK)

        // Draw fruit
        fruit.draw(canvas, paint)

        // Draw snake
        snake.draw(canvas, paint)

        // Check if snake eats fruit
        checkCollision()
    }

    private fun checkCollision() {
        val head = snake.segments[0]
        val distance = sqrt((head.first - fruit.x).pow(2) + (head.second - fruit.y).pow(2))

        if (distance < 20f) { // Snake eats fruit
            snake.grow()
            fruit.randomizePosition(width, height, context) // Spawn new fruit
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                val dx = event.x - joystickCenterX
                val dy = event.y - joystickCenterY
                val length = sqrt(dx * dx + dy * dy)

                if (length > joystickRadius) {
                    val normalizedX = dx / length
                    val normalizedY = dy / length
                    snake.setDirection(normalizedX, normalizedY)

                    // Move joystick knob
                    knobX = joystickCenterX + normalizedX * joystickRadius
                    knobY = joystickCenterY + normalizedY * joystickRadius
                } else {
                    knobX = event.x
                    knobY = event.y
                }
            }

            MotionEvent.ACTION_UP -> {
                // Joystick release hone par knob center me wapas aaye
                knobX = joystickCenterX
                knobY = joystickCenterY
            }
        }
        return true
    }
}