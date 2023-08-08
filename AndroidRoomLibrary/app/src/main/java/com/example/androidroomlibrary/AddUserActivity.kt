package com.example.androidroomlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.androidroomlibrary.database.AppDatabase
import com.example.androidroomlibrary.databinding.ActivityAddUserBinding
import com.example.androidroomlibrary.model.User

class AddUserActivity : AppCompatActivity() {
    lateinit var db: AppDatabase
    lateinit var edtName: EditText
    lateinit var edtDescription: EditText
    lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "dixit.db")
            .allowMainThreadQueries().build()

        edtName = findViewById(R.id.edt_name)
        edtDescription = findViewById(R.id.edt_description)
        btnSubmit = findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            var name = edtName.text.toString().trim()
            var des = edtDescription.text.toString().trim()

            inserUser(name, des)
        }


    }

    private fun inserUser(name: String, des: String) {
        var user = User(name = name, description = des)
        db.userdao().inserUser(user)
        Toast.makeText(applicationContext, "saved", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }
}