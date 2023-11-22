package com.example.fetchfromfile.fetch_from_file.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchfromfile.R
import com.example.fetchfromfile.fetch_from_file.model.AudioFile

class AudioListAdapter(private val context: Context,private val audioList:List<AudioFile>):
    RecyclerView.Adapter<AudioListAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val audioTittle:TextView=itemView.findViewById(R.id.tv_text_tittle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.audio_card_view,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return audioList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val audioFile=audioList[position]
        holder.audioTittle.text=audioFile.name

    }
}