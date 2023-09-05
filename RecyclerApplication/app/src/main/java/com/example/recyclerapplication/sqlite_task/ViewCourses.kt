package com.example.recyclerapplication.sqlite_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.sqlite_task.adapter.CourseRVAdapter
import com.example.recyclerapplication.sqlite_task.model.CourseModal
import com.example.recyclerapplication.sqlite_task.model.DBHandler

class ViewCourses : AppCompatActivity(), CourseRVAdapter.CourseDeleteListener {
    private var courseModalArrayList: ArrayList<CourseModal> = ArrayList()
    private lateinit var dbHandler: DBHandler
    private lateinit var courseRVAdapter: CourseRVAdapter
    private lateinit var coursesRV: RecyclerView
    private var recentlyDeletedItemId: String? = null
    private lateinit var searchEditText:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_courses)


         searchEditText = findViewById(R.id.searchEditText)
        dbHandler = DBHandler(this)
        courseModalArrayList = dbHandler.readCourses()
        courseRVAdapter = CourseRVAdapter(courseModalArrayList, this, dbHandler, this)
        coursesRV = findViewById(R.id.idRVCourses)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        coursesRV.layoutManager = linearLayoutManager
        coursesRV.adapter = courseRVAdapter


        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                val filteredList = filterData(query)
                courseRVAdapter.updateData(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed in this case
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed in this case
            }
        })


    }

    private fun filterData(query: String): ArrayList<CourseModal> {
        val filteredList = ArrayList<CourseModal>()
        for (course in courseModalArrayList) {
            if (course.courseName.contains(query, ignoreCase = true) ||
                course.courseTracks.contains(query, ignoreCase = true) ||
                course.courseDuration.contains(query, ignoreCase = true) ||
                course.courseDescription.contains(query, ignoreCase = true)
            ) {
                if (course.id != recentlyDeletedItemId) {
                    filteredList.add(course)
                }
            }
        }
        return filteredList
    }

    override fun onDeleteCourse(courseId: String, position: Int) {
        dbHandler.deleteCourse(courseId)
        recentlyDeletedItemId = courseId
        courseModalArrayList.removeAt(position)

        // Remove the item from the filtered list as well
        val filteredList = filterData(searchEditText.text.toString().trim())
        courseRVAdapter.updateData(filteredList)

        courseRVAdapter.notifyItemRemoved(position)
    }



}