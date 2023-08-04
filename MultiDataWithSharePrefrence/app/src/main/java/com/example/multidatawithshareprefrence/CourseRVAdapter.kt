// CourseRVAdapter.kt
package com.example.sharedpreferencesdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.multidatawithshareprefrence.R
import com.example.sharedpreferencesdemo.model.Course

class CourseRVAdapter(private val courseList: List<Course>) :
    RecyclerView.Adapter<CourseRVAdapter.CourseViewHolder>() {

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseNameTextView: TextView = itemView.findViewById(R.id.courseNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseNameTextView.text = course.courseName
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}
