package com.example.recyclerapplication.sqlite_task

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.recyclerapplication.R
import com.example.recyclerapplication.sqlite_task.model.CourseModal
import com.example.recyclerapplication.sqlite_task.model.DBHandler
import java.util.Calendar

@Suppress("DEPRECATION")
class MainActivity1 : AppCompatActivity() {
    private lateinit var courseNameEdt: EditText
    private lateinit var courseTracksEdt: EditText
    private lateinit var courseDurationEdt: EditText
    private lateinit var courseDescriptionEdt: EditText
    private lateinit var addCourseBtn: Button
    private lateinit var readCourseBtn: Button
    private lateinit var dbHandler: DBHandler

    private lateinit var editTextDate: EditText
    private lateinit var imageButtonDatePicker: ImageButton
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

        editTextDate = findViewById(R.id.editTextDate)
        imageButtonDatePicker = findViewById(R.id.imageButtonDatePicker)

        courseNameEdt = findViewById(R.id.idEdtCourseName)
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks)
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration)
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription)
        addCourseBtn = findViewById(R.id.idBtnAddCourse)
        readCourseBtn = findViewById(R.id.idBtnReadCourse)

        dbHandler = DBHandler(this)


        editTextDate.setOnClickListener {
            showDatePickerDialog()
        }

        // ImageButton par click karne par DatePickerDialog open karna.
        imageButtonDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        addCourseBtn.setOnClickListener {
            // below line is to get data from all edit text fields.
            val courseName = courseNameEdt.text.toString()
            val courseTracks = courseTracksEdt.text.toString()
            val courseDuration = courseDurationEdt.text.toString()
            val courseDescription = courseDescriptionEdt.text.toString()
            var courseDate = editTextDate.text.toString().trim()


            // validating if the text fields are empty or not.
            if (courseName.isEmpty() || courseTracks.isEmpty() || courseDuration.isEmpty() || courseDescription.isEmpty()) {
                Toast.makeText(this, "Please enter all the data..", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // on below line we are calling a method to add new
            // course to sqlite data and pass all our values to it.
            dbHandler.addNewCourse(
                courseName,
                courseDuration,
                courseDescription,
                courseDate,
                courseTracks
            )

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

    private fun showDatePickerDialog() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                editTextDate.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}