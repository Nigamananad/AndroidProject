package com.example.fruitapicall.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitapicall.databinding.FruitCardViewBinding
import com.example.fruitapicall.model.FruitResponseItem

class FruitListAdapter(var context: Context, var fruitList: MutableList<FruitResponseItem>) :
    RecyclerView.Adapter<FruitListAdapter.MyViewHolder>() {
    lateinit var binding: FruitCardViewBinding

    class MyViewHolder(var bind: FruitCardViewBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = FruitCardViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.bind.tvName.text = fruit.name
        holder.bind.tvFamily.text = fruit.family
        holder.bind.tvGenus.text = fruit.genus
        holder.bind.tvOrder.text = fruit.order
    }

    fun setItem(fruitList: MutableList<FruitResponseItem>) {
        this.fruitList = fruitList
        notifyDataSetChanged()
    }
}