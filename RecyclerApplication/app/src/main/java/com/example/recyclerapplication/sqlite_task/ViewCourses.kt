package com.example.recyclerapplication.sqlite_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.sqlite_task.adapter.CourseRVAdapter
import com.example.recyclerapplication.sqlite_task.model.CourseModal
import com.example.recyclerapplication.sqlite_task.model.DBHandler

class ViewCourses : AppCompatActivity() {
    private var courseModalArrayList: ArrayList<CourseModal> = ArrayList()
    private lateinit var dbHandler: DBHandler
    private lateinit var courseRVAdapter: CourseRVAdapter
    private lateinit var coursesRV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_courses)

        dbHandler = DBHandler(this)
        courseModalArrayList = dbHandler.readCourses()
        courseRVAdapter = CourseRVAdapter(courseModalArrayList, this)
        coursesRV = findViewById(R.id.idRVCourses)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        coursesRV.layoutManager = linearLayoutManager
        coursesRV.adapter = courseRVAdapter
    }
}