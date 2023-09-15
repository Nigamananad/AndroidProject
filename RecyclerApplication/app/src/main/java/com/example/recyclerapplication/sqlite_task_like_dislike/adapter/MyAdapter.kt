package com.example.recyclerapplication.sqlite_task_like_dislike.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.databinding.ItemLayoutBinding
import com.example.recyclerapplication.sqlite_task_like_dislike.model.SeriesNo

class MyAdapter(
    private var itemList: List<SeriesNo>,
    private val onItemClick: (SeriesNo,) -> Unit,
    private val onDislikeClick: (SeriesNo) -> Unit
) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private lateinit var binding: ItemLayoutBinding
    private lateinit var context: Context


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItemList: List<SeriesNo>) {
        itemList = newItemList
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.tvSeriesNo.text = item.seriesNo

        if (item.likeStatus == 1) {
            holder.binding.ibLike.setImageResource(R.drawable.baseline_thumb_up_24)
        } else {
            holder.binding.ibLike.setImageResource(R.drawable.baseline_thumb_up_off_alt_24)
        }

        if (item.disLikeStatus == -1) { // Changed from item.likeStatus to item.disLikeStatus
            holder.binding.ibDislike.setImageResource(R.drawable.baseline_thumb_down_24)
        } else {
            holder.binding.ibDislike.setImageResource(R.drawable.baseline_thumb_down_off_alt_24)
        }

        holder.binding.ibLike.setOnClickListener {
            item.likeStatus = 1
            holder.binding.ibLike.setImageResource(R.drawable.baseline_thumb_up_24)
            onItemClick(item)
        }

        holder.binding.ibDislike.setOnClickListener {
            item.disLikeStatus = -1 // Changed from item.likeStatus to item.disLikeStatus
            holder.binding.ibDislike.setImageResource(R.drawable.baseline_thumb_down_24)
            onDislikeClick(item)
        }
    }
}