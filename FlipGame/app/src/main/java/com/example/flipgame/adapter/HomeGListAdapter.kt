package com.example.flipgame.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.flipgame.R
import com.example.flipgame.activity.FlipAndMatchActivity
import com.example.flipgame.model.GameClass

class HomeGListAdapter(var context: Context, var gList: MutableList<GameClass>) :
    RecyclerView.Adapter<HomeGListAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_img: ImageView = itemView.findViewById(R.id.iv_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_gcard_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val glist = gList[position]
        holder.iv_img.setImageResource(glist.Image)
        holder.iv_img.setOnClickListener {
            when {
                (glist.id == 1) -> {

                }

                (glist.id == 2) -> {
//                    context.startActivity(Intent(context, MainActivity::class.java))
                }

                (glist.id == 3) -> {
                    context.startActivity(Intent(context, FlipAndMatchActivity::class.java))
                }

                (glist.id == 4) -> {
//                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            }
        }
    }
}