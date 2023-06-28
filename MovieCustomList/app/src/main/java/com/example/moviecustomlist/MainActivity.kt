package com.example.moviecustomlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecustomlist.Adapter.MovieListAdapter
import com.example.moviecustomlist.Model.Movie
import com.example.moviecustomlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mAdapter: MovieListAdapter

    var movieList = mutableListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title="Movie List"

        movieList.add(
            Movie(
                1,
                "Pirates of the Caribbean: Dead Man's Chest",
                "Adventure/Action",
                4.2f,
                "2006",
                R.drawable.img1
            )
        )
        movieList.add(
            Movie(
                2,
                "The LEGO Ninjago Movie",
                "Family/Comedy",
                3.5f,
                "2017",
                R.drawable.img2
            )
        )
        movieList.add(
            Movie(
                3,
                "Avatar: The Way of Water",
                "Sci-fi/Action",
                4.0f,
                "2022",
                R.drawable.img3
            )
        )
        movieList.add(Movie(4, "RRR", "Action/Drama", 4.5f, "2022", R.drawable.img4))
        movieList.add(Movie(5, "K.G.F: Chapter 2", "Action/Drama", 4.7f, "2022", R.drawable.img5))
        movieList.add(Movie(6, "Pathaan", "Action/Thriller", 3.9f, "2023", R.drawable.img6))
        movieList.add(Movie(7, "Varisu", "Drama/Action", 3.7f, "2023", R.drawable.img7))
        movieList.add(Movie(8, "Major", "Action/Drama", 4.0f, "2022", R.drawable.img8))

        mAdapter = MovieListAdapter(this, movieList)
        binding.recycleItem.layoutManager = LinearLayoutManager(this)
        binding.recycleItem.adapter = mAdapter
    }
}