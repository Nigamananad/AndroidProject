package com.example.bhagwatgita.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagwatgita.R
import com.example.bhagwatgita.model.Chapter

class ChapterAdapter(private val chapterList: List<Chapter>,private val onItemClick: (Chapter) -> Unit) :
    RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>() {

    class ChapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvChapterName: TextView = view.findViewById(R.id.tvChapterName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return ChapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chapterList[position]
        holder.tvChapterName.text = chapter.name

        holder.itemView.setOnClickListener {
            onItemClick(chapter)
        }
    }

    override fun getItemCount() = chapterList.size
}
