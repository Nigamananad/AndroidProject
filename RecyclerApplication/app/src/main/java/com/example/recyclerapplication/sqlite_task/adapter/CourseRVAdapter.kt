package com.example.recyclerapplication.sqlite_task.adapter

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.sqlite_task.model.CourseModal
import com.example.recyclerapplication.sqlite_task.model.DBHandler
import java.util.Calendar

class CourseRVAdapter(
    private var courseModalArrayList: ArrayList<CourseModal>,
    private val context: Context,
    private val dbHandler: DBHandler,
    private val deleteListener: CourseDeleteListener
) :
    RecyclerView.Adapter<CourseRVAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        internal val courseNameTV: TextView = itemView.findViewById(R.id.idTVCourseName)
        internal val courseDescTV: TextView = itemView.findViewById(R.id.idTVCourseDescription)
        internal val courseDurationTV: TextView = itemView.findViewById(R.id.idTVCourseDuration)
        internal val courseTracksTV: TextView = itemView.findViewById(R.id.idTVCourseTracks)
        internal val btnDelete: ImageButton = itemView.findViewById(R.id.btn_delete)
        internal val cardView: CardView = itemView.findViewById(R.id.card_item)
        internal val courseDate: TextView = itemView.findViewById(R.id.updateeditTextDate)
        internal val imgDate: ImageButton = itemView.findViewById(R.id.updateimageButtonDatePicker)

        lateinit var modal: CourseModal

        init {
            // Initialize modal variable with data when ViewHolder is created
            imgDate.setOnClickListener {
                openDatePicker()
            }
        }

        private fun openDatePicker() {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // Set the selected date to the modal object
                    modal.date = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    // Update the date TextView in the card view
                    courseDate.text = modal.date
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

    }

    interface CourseDeleteListener {
        fun onDeleteCourse(courseId: String, position: Int)

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
        holder.modal = courseModalArrayList[position]
        holder.courseNameTV.text = holder.modal.courseName
        holder.courseDescTV.text = holder.modal.courseDescription
        holder.courseDurationTV.text = holder.modal.courseDuration
        holder.courseTracksTV.text = holder.modal.courseTracks
        holder.courseDate.text = holder.modal.date

//        holder.imgDate.setOnClickListener {
//            val calendar = Calendar.getInstance()
//            val year = calendar.get(Calendar.YEAR)
//            val month = calendar.get(Calendar.MONTH)
//            val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//            val datePickerDialog = DatePickerDialog(
//                context,
//                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
//                    val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
//                    holder.courseDate.setText(selectedDate)
//                },
//                year,
//                month,
//                day
//            )
//            datePickerDialog.show()
//        }

        holder.btnDelete.setOnClickListener {
            val modal = courseModalArrayList[position]
            val courseId = modal.id

            // Call the delete method and notify the listener
            val deleted = dbHandler.deleteCourse(courseId)
            if (deleted) {
                Toast.makeText(context, "Course deleted", Toast.LENGTH_SHORT).show()
                // Notify the listener that a course has been deleted
                deleteListener.onDeleteCourse(courseId, position)
            } else {
                Toast.makeText(
                    context,
                    "Failed to delete course with ID: $courseId",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    fun updateData(newData: Any) {
        courseModalArrayList = newData as ArrayList<CourseModal>
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        if (position >= 0 && position < courseModalArrayList.size) {
            courseModalArrayList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    // https://chat.chatgptdemo.net/

}