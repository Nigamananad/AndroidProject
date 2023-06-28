package com.example.moviecustomlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecustomlist.Model.Movie
import com.example.moviecustomlist.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var movie = intent.getParcelableExtra<Movie>("MOVIE")

        if (movie!=null)
        {
            supportActionBar!!.title="${movie.movieName}"

            binding.ivImg.setImageResource(movie.image)
            binding.tvMovieTittle.text="${movie.movieName}"
            binding.tvMovieCategory.text="${movie.category}"
            binding.movieRattingBar.rating=movie.rating

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}