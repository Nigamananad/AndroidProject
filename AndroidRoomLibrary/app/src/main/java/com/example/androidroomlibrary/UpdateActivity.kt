package com.example.androidroomlibrary

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.example.androidroomlibrary.database.AppDatabase
import com.example.androidroomlibrary.databinding.ActivityUpdateBinding
import com.example.androidroomlibrary.model.User

@Suppress("DEPRECATION")
class UpdateActivity : AppCompatActivity() {

    lateinit var db: AppDatabase

    lateinit var edtName: EditText
    lateinit var edtDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        db = Room.databaseBuilder(this, AppDatabase::class.java, name = "dixit.db")
            .allowMainThreadQueries().build()

        edtName = findViewById(R.id.edt_name)
        edtDescription = findViewById(R.id.edt_description)

        // var user = intent.getParcelableExtra("USER",User::class.java)
        var user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("USER", User::class.java)
        } else {
            intent.getParcelableExtra("USER")
        }

        if (user != null) {
            edtName.setText(user.name)
            edtDescription.setText(user.description)
        }

        findViewById<Button>(R.id.btn_update).setOnClickListener {

            var name = edtName.text.toString().trim()
            var desc = edtDescription.text.toString().trim()

            var mUser = User(name = name, description = desc, id = user!!.id)

            db.userdao().updateUser(mUser)
            onBackPressed()
        }


    }
}