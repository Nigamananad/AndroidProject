package com.example.likemate.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.likemate.R
import com.example.likemate.model.NumberEntity
import com.example.likemate.viewmodel.NumberViewModel

class NumberAdapter(private val viewModel: NumberViewModel) :
    ListAdapter<NumberEntity, NumberAdapter.NumberViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_number, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val number = getItem(position)
        holder.bind(number)
    }

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textNumber: TextView = itemView.findViewById(R.id.textNumber)
        private val imgLike: ImageView = itemView.findViewById(R.id.imgLike)
        private val imgDislike: ImageView = itemView.findViewById(R.id.imgDislike)

        fun bind(number: NumberEntity) {
            textNumber.text = number.number.toString()

            // ✅ Like & Dislike Button UI Update
            imgLike.setImageResource(if (number.isLiked) R.drawable.ic_like_selected else R.drawable.ic_like_unselected)
            imgDislike.setImageResource(if (number.isDisliked) R.drawable.ic_dislike_selected else R.drawable.ic_dislike_unselected)

            // ✅ Like Button Click
            imgLike.setOnClickListener {
                Log.d("TAG45125", "bind: LikeClick ")
                if (!number.isLiked) {
                    Log.d("TAG45125", "bind: LikeClick1 ")
                    viewModel.onLikeClicked(number.id, true)
                }
            }

            // ✅ Dislike Button Click
            imgDislike.setOnClickListener {
                Log.d("TAG45125", "bind: DislikeClick ")

                if (!number.isDisliked) {
                    Log.d("TAG45125", "bind: DislikeClick ")
                    viewModel.onDislikeClicked(number.id, true)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<NumberEntity>() {
        override fun areItemsTheSame(oldItem: NumberEntity, newItem: NumberEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NumberEntity, newItem: NumberEntity): Boolean {
            return oldItem == newItem
        }
    }
}
