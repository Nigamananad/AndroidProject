// DisplayActivity.kt
package com.example.sharedpreferencesdemo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multidatawithshareprefrence.R
import com.example.sharedpreferencesdemo.adapter.CourseRVAdapter
import com.example.sharedpreferencesdemo.model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DisplayActivity : AppCompatActivity() {

    private lateinit var recyclerViewCourses: RecyclerView
    private lateinit var courseRVAdapter: CourseRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        recyclerViewCourses = findViewById(R.id.recyclerViewCourses)
        recyclerViewCourses.layoutManager = LinearLayoutManager(this)

        // Retrieve the course list from shared preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("courses", null)
        val type = object : TypeToken<ArrayList<Course>>() {}.type
        val courseList: ArrayList<Course> = gson.fromJson(json, type) ?: ArrayList()

        // Initialize the adapter with the course list and set it to the RecyclerView
        courseRVAdapter = CourseRVAdapter(courseList)
        recyclerViewCourses.adapter = courseRVAdapter
    }
}
