package com.example.moviecustomlist.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecustomlist.Model.Movie
import com.example.moviecustomlist.MovieActivity
import com.example.moviecustomlist.databinding.ActivityMainBinding
import com.example.moviecustomlist.databinding.ItemDialogLayoutBinding
import com.example.moviecustomlist.databinding.ItemMovieLayoutBinding

class MovieListAdapter(var context: Context, var movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {

    lateinit var binding: ItemMovieLayoutBinding

    class MyViewHolder(var bind: ItemMovieLayoutBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemMovieLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var movie = movieList[position]
        holder.bind.tvName.text = "${movie.movieName}"
        holder.bind.tvCategory.text = "${movie.category}"
        holder.bind.tvYear.text = "${movie.year}"
        holder.bind.ivImage.setImageResource(movie.image)
        holder.bind.ratingBar.rating = movie.rating

        holder.bind.cardItem.setOnClickListener {
            var intent = Intent(context, MovieActivity::class.java)

            intent.putExtra("MOVIE", movie)
            context.startActivity(intent)
        }

        holder.bind.ivImage.setOnClickListener {
            showCustomDialog(movie)
        }
    }

    private fun showCustomDialog(movie: Movie) {
        var dialogBinding = ItemDialogLayoutBinding.inflate(LayoutInflater.from(context))
        var builder = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
        var dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

        dialogBinding.ivThumbnail.setImageResource(movie.image)
    }
}