package com.example.nestedcustommovielist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedcustommovielist.Model.Movie
import com.example.nestedcustommovielist.databinding.ItemChildLayoutBinding

class ChildListAdapter(var context: Context, var movieList: MutableList<Movie>) :
    RecyclerView.Adapter<ChildListAdapter.MyViewHolder>() {

    lateinit var binding: ItemChildLayoutBinding

    class MyViewHolder(var bind: ItemChildLayoutBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemChildLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            var movie=movieList[position]
        holder.bind.ivThumbnail.setImageResource(movie.image)
    }

}