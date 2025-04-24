package com.jndevelopstudio.nonetexplore.activity.pixelpuzzle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.adapter.pixelpuzzle.PuzzleImageAdapter
import com.jndevelopstudio.nonetexplore.databinding.ActivityPixelPuzzleBinding
import com.jndevelopstudio.nonetexplore.other_class.BaseActivity
import com.jndevelopstudio.nonetexplore.utils.SwipeDirections
import com.jndevelopstudio.nonetexplore.view.GestureDetectGridView
import java.util.Random

class PixelPuzzleActivity : BaseActivity() {
    private lateinit var _binding: ActivityPixelPuzzleBinding
    private val binding get() = _binding

    companion object {
        private const val TOTAL_COLUMNS = 3
        private const val DIMENSIONS = TOTAL_COLUMNS * TOTAL_COLUMNS
        private var boardColumnWidth = 0
        private var boardColumnHeight = 0
    }

    private val tileListIndexes = mutableListOf<Int>()
    private val isSolved: Boolean
        get() {
            var solved = false
            for (i in tileListIndexes.indices) {
                if (tileListIndexes[i] == i) {
                    solved = true
                } else {
                    solved = false
                    break
                }
            }
            return solved
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPixelPuzzleBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        handleNotchScreen()
        initData()
        scrambleTileBoard()
        setTileBoardDimensions()
    }

    private fun initData() {
        binding.gestureDetectGridView.apply {
            numColumns = TOTAL_COLUMNS
            setOnSwipeListener(object : GestureDetectGridView.OnSwipeListener {
                override fun onSwipe(direction: SwipeDirections, position: Int) {
                    moveTiles(direction, position)
                }
            })
        }
        tileListIndexes += 0 until DIMENSIONS
    }

    private fun scrambleTileBoard() {
        var index: Int
        var tempIndex: Int
        val random = Random()
        for (i in tileListIndexes.size - 1 downTo 1) {
            index = random.nextInt(i + 1)
            tempIndex = tileListIndexes[index]
            tileListIndexes[index] = tileListIndexes[i]
            tileListIndexes[i] = tempIndex
        }
    }

    private fun setTileBoardDimensions() {
        val observer = binding.gestureDetectGridView.viewTreeObserver
        observer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.gestureDetectGridView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val displayWidth = binding.gestureDetectGridView.measuredWidth
                val displayHeight = binding.gestureDetectGridView.measuredHeight
                val statusBarHeight = getStatusBarHeight(applicationContext)
                val requiredHeight = displayHeight - statusBarHeight
                boardColumnWidth = displayWidth / TOTAL_COLUMNS
                boardColumnHeight = requiredHeight / TOTAL_COLUMNS
                displayTileBoard()
            }
        })
    }

    @SuppressLint("InternalInsetResource")
    private fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * Used for both init and every time a new swap move is made by the user.
     */
//    private fun displayTileBoard() {
//        val tileImages = mutableListOf<ImageView>()
//        var tileImage: ImageView
//        tileListIndexes.forEach { i ->
//            tileImage = ImageView(this)
//            when (i) {
//                0 -> tileImage.setBackgroundResource(R.drawable.pz1_0)
//                1 -> tileImage.setBackgroundResource(R.drawable.pz1_1)
//                2 -> tileImage.setBackgroundResource(R.drawable.pz1_2)
//                3 -> tileImage.setBackgroundResource(R.drawable.pz1_3)
//                4 -> tileImage.setBackgroundResource(R.drawable.pz1_4)
//                5 -> tileImage.setBackgroundResource(R.drawable.pz1_5)
//                6 -> tileImage.setBackgroundResource(R.drawable.pz1_6)
//                7 -> tileImage.setBackgroundResource(R.drawable.pz1_7)
////                8 -> tileImage.setBackgroundResource(R.drawable.pz1_8)
//            }
//            tileImages.add(tileImage)
//        }
//        binding.gestureDetectGridView.adapter =
//            PuzzleImageAdapter(tileImages, boardColumnWidth, boardColumnHeight)
//    }

    private fun displayTileBoard() {
        val tileViews = mutableListOf<View>()
        tileListIndexes.forEachIndexed { index, tileIndex ->
            // Create a layout for each tile
            val tileLayout = FrameLayout(this).apply {
                layoutParams = ViewGroup.LayoutParams(boardColumnWidth, boardColumnHeight)
            }

            // Image for the tile
            val tileImage = ImageView(this).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                scaleType = ImageView.ScaleType.CENTER_CROP

                when (tileIndex) {
                    0 -> setBackgroundResource(R.drawable.pz1_0)
                    1 -> setBackgroundResource(R.drawable.pz1_1)
                    2 -> setBackgroundResource(R.drawable.pz1_2)
                    3 -> setBackgroundResource(R.drawable.pz1_3)
                    4 -> setBackgroundResource(R.drawable.pz1_4)
                    5 -> setBackgroundResource(R.drawable.pz1_5)
                    6 -> setBackgroundResource(R.drawable.pz1_6)
                    7 -> setBackgroundResource(R.drawable.pz1_7)
                    // Add more resources as needed
                }
            }

            // Number overlay
            val tileNumber = TextView(this).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                }
                text = (tileIndex + 1).toString() // Display the tile number
                setTextColor(Color.WHITE) // White text color
                textSize = 18f // Adjust text size
                setTypeface(null, Typeface.BOLD) // Bold text
                visibility = if (tileIndex == tileListIndexes.size - 1) View.INVISIBLE else View.VISIBLE
            }

            // Add image and text to the layout
            tileLayout.addView(tileImage)
            tileLayout.addView(tileNumber)

            tileViews.add(tileLayout)
        }

        // Set the adapter
        binding.gestureDetectGridView.adapter =
            PuzzleImageAdapter(tileViews, boardColumnWidth, boardColumnHeight)
    }



    private fun displayToast(@StringRes textResId: Int) {
        Toast.makeText(this, getString(textResId), Toast.LENGTH_SHORT).show()
    }

    private fun moveTiles(direction: SwipeDirections, position: Int) {
        val newPosition = when (direction) {
            SwipeDirections.RIGHT -> position + 1
            SwipeDirections.LEFT -> position - 1
            SwipeDirections.DOWN -> position + TOTAL_COLUMNS
            SwipeDirections.UP -> position - TOTAL_COLUMNS
        }
        if (isMoveValid(position, newPosition)) {
            animateTileSwap(position, newPosition, direction)
            swapTile(position, newPosition)
        } else {
            displayToast(R.string.invalid_move)
        }
    }

    private fun isMoveValid(currentPosition: Int, newPosition: Int): Boolean {
        // Check if the new position is within bounds and if it represents a valid move
        return newPosition in 0 until DIMENSIONS && (currentPosition % TOTAL_COLUMNS == newPosition % TOTAL_COLUMNS || currentPosition / TOTAL_COLUMNS == newPosition / TOTAL_COLUMNS)
    }

    private fun swapTile(currentPosition: Int, newPosition: Int) {
        val tempIndex = tileListIndexes[currentPosition]
        tileListIndexes[currentPosition] = tileListIndexes[newPosition]
        tileListIndexes[newPosition] = tempIndex
        displayTileBoard()
        if (isSolved) {
            displayToast(R.string.winner)
        }
    }

    private fun animateTileSwap(fromPosition: Int, toPosition: Int, direction: SwipeDirections) {
        val fromView = binding.gestureDetectGridView.getChildAt(fromPosition)
        val toView = binding.gestureDetectGridView.getChildAt(toPosition)
        when (direction) {
            SwipeDirections.RIGHT -> {
                fromView?.animate()?.translationX(toView?.x ?: 0f)?.setDuration(300)?.start()
                toView?.animate()?.translationX(fromView?.x ?: 0f)?.setDuration(300)?.start()
            }

            SwipeDirections.LEFT -> {
                fromView?.animate()?.translationX(toView?.x ?: 0f)?.setDuration(300)?.start()
                toView?.animate()?.translationX(fromView?.x ?: 0f)?.setDuration(300)?.start()
            }

            SwipeDirections.UP -> {
                fromView?.animate()?.translationY(toView?.y ?: 0f)?.setDuration(300)?.start()
                toView?.animate()?.translationY(fromView?.y ?: 0f)?.setDuration(300)?.start()
            }

            SwipeDirections.DOWN -> {
                fromView?.animate()?.translationY(toView?.y ?: 0f)?.setDuration(300)?.start()
                toView?.animate()?.translationY(fromView?.y ?: 0f)?.setDuration(300)?.start()
            }
        }
    }
}