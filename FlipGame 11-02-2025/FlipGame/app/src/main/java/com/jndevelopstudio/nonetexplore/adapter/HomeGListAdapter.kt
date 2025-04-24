package com.jndevelopstudio.nonetexplore.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jndevelopstudio.nonetexplore.R
import com.jndevelopstudio.nonetexplore.activity.FlipAndMatchActivity
import com.jndevelopstudio.nonetexplore.activity.pixelpuzzle.PixelPuzzleActivity
import com.jndevelopstudio.nonetexplore.activity.pixelpuzzle.PuzzleActivity
import com.jndevelopstudio.nonetexplore.activity.tictactoe.ChooseActivity
import com.jndevelopstudio.nonetexplore.model.GameClass

class HomeGListAdapter(
    var context: Context,
    var gList: MutableList<GameClass>,
    val onButtonClick: (Int) -> Unit
) :
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
            onButtonClick(glist.id)
        }
    }


}