package com.example.adduserproject.volley_library_example.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.adduserproject.R
import com.example.adduserproject.volley_library_example.NextActivity
import com.example.adduserproject.volley_library_example.model.CourseRVModal
import com.squareup.picasso.Picasso

class CourseRVAdapter(
    private var courseList: ArrayList<CourseRVModal>, private var context: Context
) : RecyclerView.Adapter<CourseRVAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseRVAdapter.CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.course_rv_item,
            parent, false
        )
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseRVAdapter.CourseViewHolder, position: Int) {
        val data = courseList[position]
        holder.courseNameTV.text = courseList.get(position).courseName
        Picasso.get().load(courseList.get(position).courseImg).into(holder.courseIV)

        holder.cardView.setOnClickListener {
            val intent = Intent(context, NextActivity::class.java)
            intent.putParcelableArrayListExtra("IMAGES_LIST", courseList)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourse)
        val courseIV: ImageView = itemView.findViewById(R.id.idIVCourse)
        val cardView: CardView = itemView.findViewById(R.id.card_item)
    }
}
