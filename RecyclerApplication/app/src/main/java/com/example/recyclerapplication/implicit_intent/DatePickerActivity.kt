package com.example.recyclerapplication.implicit_intent

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.recyclerapplication.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DatePickerActivity : AppCompatActivity() {
    private lateinit var dateTextView: TextView
    private val calendar = Calendar.getInstance()
    private lateinit var datePickerButton: ImageButton
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)

        dateTextView = findViewById(R.id.dateTextView)
        datePickerButton = findViewById(R.id.datePickerButton)
        dateTextView.text = "19/02/1999" // Set the initial date in the TextView

        datePickerButton.setOnClickListener {
            openDatePickerDialog()
        }
    }

    private fun openDatePickerDialog() {
        val currentDate = dateTextView.text.toString()
        val date = dateFormat.parse(currentDate) ?: Date()

        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)

                val formattedDate = dateFormat.format(calendar.time)
                dateTextView.text = formattedDate
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}