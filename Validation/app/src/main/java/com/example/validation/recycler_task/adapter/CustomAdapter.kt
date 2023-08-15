package com.example.validation.recycler_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.validation.R
import com.example.validation.recycler_task.model.DataItem
import com.example.validation.recycler_task.model.OnDeleteClickListener


class CustomAdapter(private val dataList: List<DataItem>,private val onDeleteClickListener: OnDeleteClickListener): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.text1TextView.text = item.text1
        holder.text2TextView.text = item.text2

        holder.deleteButton.setOnClickListener {
            onDeleteClickListener.onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1TextView: TextView = itemView.findViewById(R.id.text1TextView)
        val text2TextView: TextView = itemView.findViewById(R.id.text2TextView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)

    }
}