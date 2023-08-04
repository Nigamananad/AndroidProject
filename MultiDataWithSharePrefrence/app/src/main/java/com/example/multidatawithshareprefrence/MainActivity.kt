// MainActivity.kt
package com.example.sharedpreferencesdemo

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.multidatawithshareprefrence.R
import com.example.sharedpreferencesdemo.model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var editTextLanguage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextLanguage = findViewById(R.id.editTextLanguage)

        val buttonSave: Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            saveCourse()
        }
    }

    private fun saveCourse() {
        val courseName = editTextLanguage.text.toString()

        // Retrieve existing course list from shared preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("courses", null)
        val type = object : TypeToken<ArrayList<Course>>() {}.type
        val courseList: ArrayList<Course> = gson.fromJson(json, type) ?: ArrayList()

        // Add new course to the list
        val newCourse = Course(courseName)
        courseList.add(newCourse)

        // Save the updated list back to shared preferences
        val editor = sharedPreferences.edit()
        val updatedJson = gson.toJson(courseList)
        editor.putString("courses", updatedJson)
        editor.apply()

        editTextLanguage.text.clear()
    }
}
