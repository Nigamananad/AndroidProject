package com.example.apnibook.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apnibook.ClientBookActivity
import com.example.apnibook.StockBookActivity
import com.example.apnibook.databinding.HomeCardViewBinding
import com.example.apnibook.dataclass.Home

class HomeListAdapter(var context: Context, var homeCard: MutableList<Home>) :
    RecyclerView.Adapter<HomeListAdapter.MyHomeViewHolder>() {

    lateinit var binding: HomeCardViewBinding

    class MyHomeViewHolder(var bind: HomeCardViewBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHomeViewHolder {
        binding = HomeCardViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeCard.size
    }

    override fun onBindViewHolder(holder: MyHomeViewHolder, position: Int) {
        val homeCardList = homeCard[position]
        holder.bind.ivClient.setImageResource(homeCardList.image)
        holder.bind.tvName.text = homeCardList.name

        holder.bind.listBook.setOnClickListener {
            when{
                (homeCardList.id == 1) -> {
                    val intent = Intent(context, ClientBookActivity::class.java)
                    context.startActivity(intent)
                }
                (homeCardList.id == 2) -> {
                    val intent2 = Intent(context,StockBookActivity::class.java)
                    context.startActivity(intent2)
                }
                (homeCardList.id == 3) -> {
                    val intent3 = Intent(context,StockBookActivity::class.java)
                    context.startActivity(intent3)
                }
                (homeCardList.id == 4) -> {
                    val intent4 = Intent(context,StockBookActivity::class.java)
                    context.startActivity(intent4)
                }
            }

        }
    }

}