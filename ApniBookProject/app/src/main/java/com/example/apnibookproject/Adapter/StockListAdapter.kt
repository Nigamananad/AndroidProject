package com.example.apnibookproject.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apnibookproject.AddStockActivity
import com.example.apnibookproject.StockUpdateActivity
import com.example.apnibookproject.databinding.StockCardViewBinding
import com.example.apnibookproject.dataclass.Client
import com.example.apnibookproject.dataclass.Stock
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StockListAdapter(var context: Context, var stockList: MutableList<Stock>) :
    RecyclerView.Adapter<StockListAdapter.MyViewHolder>() {

    lateinit var mRef: DatabaseReference
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
        mRef = FirebaseDatabase.getInstance().reference
        var stock = stockList[position]
        holder.bind.tvStockTittle.text = stock.tittle
        holder.bind.tvStockQuantity.text = stock.quantity

        holder.bind.stockCardView.setOnClickListener {
            var intent = Intent(context, StockUpdateActivity::class.java)
            intent.putExtra("STOCK", stock)
            context.startActivity(intent)
        }

        holder.bind.stockCardView.setOnLongClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Delete")
                    .setMessage("you are delete the Stock")
                    .setPositiveButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            mRef.child("Stock-node").child(stock.id).removeValue()
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {

                                    }
                                }
                        })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
            }.show()
            return@setOnLongClickListener true
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(stockList: MutableList<Stock>) {
        this.stockList = stockList
        notifyDataSetChanged()
    }
}