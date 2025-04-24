package com.jndevelopstudio.nonetexplore.activity.pixelpuzzle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.activity.pixelpuzzle.ui.theme.FlipGameTheme
import com.jndevelopstudio.nonetexplore.model.ImagePuzzle
import kotlin.math.abs

class PuzzleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlipGameTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Set the background image
                    Image(
                        painter = painterResource(id = R.drawable.allbg), // Your background image
                        contentDescription = "Background",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Center the puzzle grid
                    SlidingImagePuzzleGame(context = this@PuzzleActivity)
                }
            }
        }
    }
}

@Composable
fun SlidingImagePuzzleGame(context: Context) {
    var grid by remember { mutableStateOf(generateImageGrid()) }
    var emptyPosition by remember { mutableStateOf(findEmptyPosition(grid)) }
    var isSolved by remember { mutableStateOf(false) } // Track if the puzzle is solved
    var fadeOutCompleted by remember { mutableStateOf(false) } // Track when fade-out is done

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!isSolved) {
            // Display the puzzle grid
            Canvas(
                modifier = Modifier
                    .size(400.dp)
                    .padding(start = 2.dp, end = 2.dp)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                val direction = getDragDirection(dragAmount)
                                if (direction != null) {
                                    val touchedBox =
                                        findTouchedBox(change.position, grid.size, size.width / 3f)
                                    if (touchedBox != null) {
                                        val (newGrid, newEmptyPosition) = grid.tryMove(
                                            direction,
                                            emptyPosition,
                                            touchedBox
                                        )
                                        grid = newGrid
                                        emptyPosition = newEmptyPosition

                                        // Check if the puzzle is solved
                                        if (isImagePuzzleSolved(grid)) {
                                            isSolved = true // Mark as solved
                                        }
                                    }
                                }
                            }
                        )
                    }
            ) {
                // Draw puzzle background
                drawRect(
                    color = Color(0xFF8B4513), // Brown color
                    size = Size(
                        size.width,
                        (size.height / 1.5f) + 270f
                    )
                )

                // Draw puzzle grid
                drawImageGrid(grid, context)
            }

        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                PuzzleSolvedAnimations { fadeOutCompleted = true }
            }
            // Sequential Animation: Fade Out Grid -> Fade In Full Image
            if (fadeOutCompleted) {
                // First step: Fade out the grid
                FadeInFullImage()
            }
        }
    }
}

@Composable
fun PuzzleSolvedAnimations(onAnimationEnd: () -> Unit) {
    val fadeOutCompleted by remember { mutableStateOf(false) }

    if (!fadeOutCompleted) {
        LottieCelebrationAnimation { // Play celebration animation
            onAnimationEnd() // Notify when it's done
        }
    } else {
        GridFadeOutAnimation { // Fade out grid and transition to the solved image
            onAnimationEnd()
        }
    }
}

@Composable
fun LottieCelebrationAnimation(onAnimationEnd: () -> Unit) {
    // Lottie animation file
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_puz))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1 // Play animation once
    )

    // Lottie Animation
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier.size(400.dp) // Size of the Lottie animation
    )

    // Trigger onAnimationEnd once the animation completes
    LaunchedEffect(progress) {
        if (progress == 1f) { // Check if animation is complete
            onAnimationEnd() // Notify that the animation has finished
        }
    }
}


@Suppress("DEPRECATION")
@Composable
fun LottieCelebrationAnimation() {
    // Lottie animation file
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_puz))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1 // Play animation once
    )

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier.size(400.dp) // Size of the Lottie animation
    )
}

@Composable
fun GridFadeOutAnimation(onAnimationEnd: () -> Unit) {
    var alpha by remember { mutableFloatStateOf(1f) }
    LaunchedEffect(Unit) {
        // Animate alpha to 0 over 500ms
        Animatable(initialValue = 1f).animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500)
        ) {
            alpha = value
        }
        onAnimationEnd()
    }

    Canvas(
        modifier = Modifier
            .size(400.dp)
            .graphicsLayer(alpha = alpha) // Apply alpha during animation
    ) {
        // Optionally draw grid background or placeholder
    }
}

@Composable
fun FadeInFullImage() {
    var alpha by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(Unit) {
        // Animate alpha to 1 over 500ms
        Animatable(initialValue = 0f).animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500)
        ) {
            alpha = value
        }
    }

    Image(
        painter = painterResource(id = R.drawable.puz2), // Your solved image
        contentDescription = "Solved Image",
        modifier = Modifier
            .size(400.dp)
            .padding(1.dp)
            .graphicsLayer(alpha = alpha), // Apply alpha during animation
        contentScale = ContentScale.Crop
    )
}

enum class Direction { UP, DOWN, LEFT, RIGHT }

// Function to determine the direction of the swipe.
fun getDragDirection(dragAmount: Offset): Direction? {
    return when {
        //abs will always give +ve number either we pass +ve or -ve
        abs(dragAmount.x) > abs(dragAmount.y) -> {  //we are swiping in x-axis
            if (dragAmount.x > 0) Direction.RIGHT else Direction.LEFT
        }

        abs(dragAmount.y) > abs(dragAmount.x) -> {  //we are swiping in y-axis
            if (dragAmount.y > 0) Direction.DOWN else Direction.UP
        }

        else -> null
    }
}

// Function to find the box that was touched based on the touch position.
fun findTouchedBox(position: Offset, gridSize: Int, cellSize: Float): Pair<Int, Int>? {
    val x = (position.x / cellSize).toInt()
    val y = (position.y / cellSize).toInt()
    return if (x in 0 until gridSize && y in 0 until gridSize) x to y else null
}

// Function to find the position of the empty space in the grid.
fun findEmptyPosition(grid: List<List<ImagePuzzle>>): Pair<Int, Int> {
    grid.forEachIndexed { y, row ->
        row.forEachIndexed { x, puzzlePiece ->
            Log.d("Puzzle", "Checking position ($x, $y): ${puzzlePiece.number}")
            if (puzzlePiece.number == "0") { // Check if this cell is the empty space
                return x to y
            }
        }
    }
    throw IllegalStateException("No empty space found in the grid")
}

fun DrawScope.drawImageGrid(grid: List<List<ImagePuzzle>>, context: Context) {
    val cellSize = size.width / grid.size
    grid.forEachIndexed { y, row ->
        row.forEachIndexed { x, puzzlePiece ->
            if (puzzlePiece.imageId != 0) {
                drawImageBox(
                    puzzlePiece = puzzlePiece,
                    x = x,
                    y = y,
                    cellSize = cellSize,
                    padding = 1.dp,
                    context = context
                )
            }
        }
    }
}


fun generateImageGrid(): List<List<ImagePuzzle>> {
    val images = listOf(
        ImagePuzzle("1", R.drawable.pz1_0),
        ImagePuzzle("2", R.drawable.pz1_1),
        ImagePuzzle("3", R.drawable.pz1_2),
        ImagePuzzle("4", R.drawable.pz1_3),
        ImagePuzzle("5", R.drawable.pz1_4),
        ImagePuzzle("6", R.drawable.pz1_5),
        ImagePuzzle("7", R.drawable.pz1_6),
        ImagePuzzle("8", R.drawable.pz1_7),
        ImagePuzzle("0", 0) // 0 represents the empty space
    ).shuffled()

    return images.chunked(3)
}

fun isImagePuzzleSolved(grid: List<List<ImagePuzzle>>): Boolean {
    val winningGrid = listOf(
        listOf(
            ImagePuzzle("1", R.drawable.pz1_0),
            ImagePuzzle("2", R.drawable.pz1_1),
            ImagePuzzle("3", R.drawable.pz1_2)
        ),
        listOf(
            ImagePuzzle("4", R.drawable.pz1_3),
            ImagePuzzle("5", R.drawable.pz1_4),
            ImagePuzzle("6", R.drawable.pz1_5)
        ),
        listOf(
            ImagePuzzle("7", R.drawable.pz1_6),
            ImagePuzzle("8", R.drawable.pz1_7),
            ImagePuzzle("0", 0) // Empty space at the end
        )
    )

    // Check if the current grid matches the winning grid
    return grid.flatten().zip(winningGrid.flatten()).all { (currentPiece, winningPiece) ->
        currentPiece.number == winningPiece.number && currentPiece.imageId == winningPiece.imageId
    }
}

fun DrawScope.drawImageBox(
    puzzlePiece: ImagePuzzle,
    x: Int,
    y: Int,
    cellSize: Float,
    padding: Dp,
    context: Context
) {
    val boxSize = cellSize - padding.toPx()
    val left = x * cellSize + padding.toPx()
    val top = y * cellSize + padding.toPx()

    // Draw the brown background
    drawRect(
        color = Color(0xFF8B4513), // Brown color
        topLeft = Offset(left, top),
        size = Size(boxSize, boxSize)
    )

    // Draw the puzzle piece image
    val androidBitmap =
        android.graphics.BitmapFactory.decodeResource(context.resources, puzzlePiece.imageId)
    drawIntoCanvas { canvas ->
        val paint = android.graphics.Paint()

        val srcRect = android.graphics.Rect(0, 0, androidBitmap.width, androidBitmap.height)
        val destRect = android.graphics.RectF(
            left,
            top,
            left + boxSize,
            top + boxSize
        )

        canvas.nativeCanvas.drawBitmap(
            androidBitmap,
            srcRect,
            destRect,
            paint
        )
    }

    // Draw the number overlay
    drawIntoCanvas { canvas ->
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.WHITE
            textSize = boxSize / 6 // Adjust text size relative to the box size
            isAntiAlias = true
            setShadowLayer(
                2f,
                1f,
                1f,
                android.graphics.Color.BLACK
            ) // Add a shadow for better visibility
        }
        val text = puzzlePiece.number
        val textWidth = paint.measureText(text)
        val textX = left + boxSize - textWidth - 8.dp.toPx() // Align to bottom-right with padding
        val textY = top + boxSize - 8.dp.toPx()
        canvas.nativeCanvas.drawText(text, textX, textY, paint)
    }
}


fun List<List<ImagePuzzle>>.tryMove(
    direction: Direction,
    emptyPosition: Pair<Int, Int>,
    touchedBox: Pair<Int, Int>
): Pair<List<List<ImagePuzzle>>, Pair<Int, Int>> {
    val (emptyX, emptyY) = emptyPosition
    val (touchedX, touchedY) = touchedBox
    val newGrid = this.map { it.toMutableList() }

    return when (direction) {
        Direction.UP -> if (touchedX == emptyX && touchedY == emptyY + 1) {
            // Move the box down if the empty space is directly below it.
            val temp = newGrid[emptyY][emptyX]
            newGrid[emptyY][emptyX] = newGrid[touchedY][touchedX]
            newGrid[touchedY][touchedX] = temp
            newGrid to (touchedX to touchedY)
        } else this to emptyPosition

        Direction.DOWN -> if (touchedX == emptyX && touchedY == emptyY - 1) {
            // Move the box up if the empty space is directly above it.
            val temp = newGrid[emptyY][emptyX]
            newGrid[emptyY][emptyX] = newGrid[touchedY][touchedX]
            newGrid[touchedY][touchedX] = temp
            newGrid to (touchedX to touchedY)
        } else this to emptyPosition

        Direction.LEFT -> if (touchedY == emptyY && touchedX == emptyX + 1) {
            // Move the box left if the empty space is directly to the left of it.
            val temp = newGrid[emptyY][emptyX]
            newGrid[emptyY][emptyX] = newGrid[touchedY][touchedX]
            newGrid[touchedY][touchedX] = temp
            newGrid to (touchedX to touchedY)
        } else this to emptyPosition

        Direction.RIGHT -> if (touchedY == emptyY && touchedX == emptyX - 1) {
            // Move the box right if the empty space is directly to the right of it.
            val temp = newGrid[emptyY][emptyX]
            newGrid[emptyY][emptyX] = newGrid[touchedY][touchedX]
            newGrid[touchedY][touchedX] = temp
            newGrid to (touchedX to touchedY)
        } else this to emptyPosition
    }
}

