package com.example.apicall.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicall.databinding.CardViewBinding
import com.example.apicall.model.Data

class MovieListAdapter(var context: Context, var movieList: MutableList<Data>) :
    RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {
    lateinit var binding: CardViewBinding

    class MyViewHolder(var bind: CardViewBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CardViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind.tvName.text = movie.name
        holder.bind.tvCategory.text = movie.category
        holder.bind.tvDescription.text = movie.desc

        Glide
            .with(context)
            .load(movie.imageUrl)
            .into(holder.bind.ivImage)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(movieList: MutableList<Data>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

}