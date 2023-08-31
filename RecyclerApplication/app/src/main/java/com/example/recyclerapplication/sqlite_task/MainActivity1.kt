package com.example.recyclerapplication.sqlite_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.recyclerapplication.R
import com.example.recyclerapplication.sqlite_task.model.CourseModal
import com.example.recyclerapplication.sqlite_task.model.DBHandler

@Suppress("DEPRECATION")
class MainActivity1 : AppCompatActivity() {
    private lateinit var courseNameEdt: EditText
    private lateinit var courseTracksEdt: EditText
    private lateinit var courseDurationEdt: EditText
    private lateinit var courseDescriptionEdt: EditText
    private lateinit var addCourseBtn: Button
    private lateinit var readCourseBtn: Button
    private lateinit var dbHandler: DBHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

//        val selectedCourse = intent.getSerializableExtra("selectedCourse") as? CourseModal
//        if (selectedCourse != null) {
//            // Use the selectedCourse data to update your UI in MainActivity1
//            // For example:
//            courseNameEdt.setText(selectedCourse.courseName)
//            courseTracksEdt.setText(selectedCourse.courseTracks)
//            courseDurationEdt.setText(selectedCourse.courseDuration)
//            courseDescriptionEdt.setText(selectedCourse.courseDescription)
//        }
        courseNameEdt = findViewById(R.id.idEdtCourseName)
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks)
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration)
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription)
        addCourseBtn = findViewById(R.id.idBtnAddCourse)
        readCourseBtn = findViewById(R.id.idBtnReadCourse)

        dbHandler = DBHandler(this)

        addCourseBtn.setOnClickListener {
            // below line is to get data from all edit text fields.
            val courseName = courseNameEdt.text.toString()
            val courseTracks = courseTracksEdt.text.toString()
            val courseDuration = courseDurationEdt.text.toString()
            val courseDescription = courseDescriptionEdt.text.toString()

            // validating if the text fields are empty or not.
            if (courseName.isEmpty() || courseTracks.isEmpty() || courseDuration.isEmpty() || courseDescription.isEmpty()) {
                Toast.makeText(this, "Please enter all the data..", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // on below line we are calling a method to add new
            // course to sqlite data and pass all our values to it.
            dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks)

            // after adding the data we are displaying a toast message.
            Toast.makeText(this, "Course has been added.", Toast.LENGTH_SHORT).show()
            courseNameEdt.setText("")
            courseDurationEdt.setText("")
            courseTracksEdt.setText("")
            courseDescriptionEdt.setText("")
        }

        readCourseBtn.setOnClickListener {
            // opening a new activity via an intent.
            val intent = Intent(this, ViewCourses::class.java)
            startActivity(intent)
        }
    }
}