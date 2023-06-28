package com.example.nestedcustommovielist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedcustommovielist.Model.Category
import com.example.nestedcustommovielist.databinding.ItemParentLayoutBinding

class ParentListAdapter(var context: Context,var categoryList:MutableList<Category>):RecyclerView.Adapter<ParentListAdapter.MyViewHolder>() {

    lateinit var binding: ItemParentLayoutBinding
    class MyViewHolder(var bind:ItemParentLayoutBinding):RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding=ItemParentLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category=categoryList[position]
        holder.bind.tvTitle.text= category.tittle

        val childAdapter=ChildListAdapter(context,category.movielist)
        holder.bind.childRecyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        holder.bind.childRecyclerView.adapter=childAdapter
    }
}