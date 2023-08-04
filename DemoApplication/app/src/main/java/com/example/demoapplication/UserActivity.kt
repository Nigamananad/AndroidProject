package com.example.demoapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapplication.adapter.CourseRVAdapter
import com.example.demoapplication.databinding.ActivityUserBinding
import com.example.demoapplication.model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding

    lateinit var languageRV: RecyclerView
    lateinit var saveBtn: Button
    lateinit var lngEdt: EditText

    lateinit var courseList: ArrayList<Course>
    lateinit var courseRVAdapter: CourseRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        languageRV = binding.idRVLanguages
        saveBtn = binding.idBtnSaveList
        lngEdt = binding.idEdtLanguage

        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("courses", null)

        val type: Type = object : TypeToken<ArrayList<Course>>() {}.type
        courseList = gson.fromJson(json, type) ?: ArrayList()

        // Initialize RecyclerView with LinearLayoutManager
        languageRV.layoutManager = LinearLayoutManager(this)

        courseRVAdapter = CourseRVAdapter(courseList)
        languageRV.adapter = courseRVAdapter

        saveBtn.setOnClickListener {
            if (lngEdt.text.toString().isNotEmpty()) {
                addItemToList(lngEdt.text.toString())
                lngEdt.text.clear() // Clear the EditText after adding a course
            } else {
                Toast.makeText(this, "Please enter a language name.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addItemToList(lngName: String) {
        courseList.add(Course(lngName))
        courseRVAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Save the course list to shared preferences when the activity is destroyed
        val sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(courseList)
        editor.putString("courses", json)
        editor.apply()
    }
}
