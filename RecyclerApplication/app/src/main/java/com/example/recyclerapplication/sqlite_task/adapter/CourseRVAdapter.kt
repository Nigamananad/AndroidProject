package com.example.recyclerapplication.sqlite_task.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.sqlite_task.MainActivity1
import com.example.recyclerapplication.sqlite_task.model.CourseModal
import com.example.recyclerapplication.sqlite_task.model.DBHandler

class CourseRVAdapter(
    private val courseModalArrayList: ArrayList<CourseModal>,
    private val context: Context
) :
    RecyclerView.Adapter<CourseRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        internal val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourseName)
        internal val courseDescTV: TextView = itemView.findViewById(R.id.idTVCourseDescription)
        internal val courseDurationTV: TextView = itemView.findViewById(R.id.idTVCourseDuration)
        internal val courseTracksTV: TextView = itemView.findViewById(R.id.idTVCourseTracks)

        private var isLongClick = false

        init {
//            itemView.setOnClickListener {
//                if (!isLongClick) {
//                    // Handle normal click here
//                    val position = adapterPosition
//                    if (position != RecyclerView.NO_POSITION) {
//                        // Perform your normal click action
//                    }
//                }
//            }

            itemView.setOnLongClickListener {
                isLongClick = true
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val courseToDelete = courseModalArrayList[position]
                    deleteCourse(courseToDelete)

                    courseModalArrayList.removeAt(position)
                    notifyItemRemoved(position)
                }
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.course_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return courseModalArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modal = courseModalArrayList[position]
        holder.courseNameTV.text = modal.courseName
        holder.courseDescTV.text = modal.courseDescription
        holder.courseDurationTV.text = modal.courseDuration
        holder.courseTracksTV.text = modal.courseTracks
    }

    // Add this method to delete a course from the database
    private fun deleteCourse(course: CourseModal) {
        val dbHandler = DBHandler(context)
        dbHandler.deleteCourse(course)

        // After deleting, update the courseModalArrayList with fresh data
        courseModalArrayList.clear()
        courseModalArrayList.addAll(dbHandler.readCourses())
        notifyDataSetChanged()
    }
}