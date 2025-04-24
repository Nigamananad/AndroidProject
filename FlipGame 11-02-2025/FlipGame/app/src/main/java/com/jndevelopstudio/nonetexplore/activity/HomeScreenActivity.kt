package com.jndevelopstudio.nonetexplore.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.activity.pixelpuzzle.PixelPuzzleActivity
import com.jndevelopstudio.nonetexplore.activity.pixelpuzzle.PuzzleActivity
import com.jndevelopstudio.nonetexplore.activity.tictactoe.ChooseActivity
import com.jndevelopstudio.nonetexplore.adapter.HomeGListAdapter
import com.jndevelopstudio.nonetexplore.model.GameClass
import com.jndevelopstudio.nonetexplore.other_class.BaseActivity
import com.sanchayastudio.savethebunny.GameView

class HomeScreenActivity : BaseActivity() {
    private lateinit var gListAdapter: HomeGListAdapter
    private var gList = mutableListOf<GameClass>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        handleNotchScreen()
        recyclerView = findViewById(R.id.rv_gList)

        gList.add(GameClass(1, R.drawable.tictactoe, ""))
        gList.add(GameClass(2, R.drawable.dotboxes, ""))
        gList.add(GameClass(3, R.drawable.flipmatch, ""))
        gList.add(GameClass(4, R.drawable.puzzle, ""))

        gListAdapter = HomeGListAdapter(this, gList) { id ->
            when (id) {
                1 -> {
                    startActivity(Intent(this, ChooseActivity::class.java))
                }

                2 -> {
                    startGame()
                }

                3 -> {
                    startActivity(Intent(this, FlipAndMatchActivity::class.java))
                }

                4 -> {
                    startActivity(Intent(this, PuzzleActivity::class.java))
                }
            }
        }
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = gListAdapter
    }

    private fun  startGame() {
        val gameView = GameView(this)
        setContentView(gameView)
    }
}