package com.example.apnibookproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apnibookproject.databinding.StockCardViewBinding
import com.example.apnibookproject.dataclass.Client
import com.example.apnibookproject.dataclass.Stock

class StockListAdapter(var context: Context, var stockList: MutableList<Stock>) :
    RecyclerView.Adapter<StockListAdapter.MyViewHolder>() {
    lateinit var binding: StockCardViewBinding

    class MyViewHolder(var bind: StockCardViewBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = StockCardViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var stock = stockList[position]
        holder.bind.tvStockTittle.text = stock.tittle
        holder.bind.tvStockQuantity.text = stock.quantity
    }

    fun setItems(stockList:MutableList<Stock>){
        this.stockList = stockList
        notifyDataSetChanged()
    }
}