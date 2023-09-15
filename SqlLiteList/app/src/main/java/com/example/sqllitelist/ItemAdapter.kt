package com.example.sqllitelist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqllitelist.databinding.AdapterListviewBinding

class ItemAdapter(
    private var itemList: List<Item>,
    private val onItemClick: (Item, ) -> Unit,
    private val onDislikeClick: (Item) -> Unit
) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private lateinit var binding: AdapterListviewBinding
    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItemList: List<Item>) {
        itemList = newItemList
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: AdapterListviewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = AdapterListviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.itemTextView.text = item.name
        holder.binding.likeButton.setOnClickListener {
            onItemClick(item)
        }
        holder.binding.dislikeButton.setOnClickListener {
            onDislikeClick(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
