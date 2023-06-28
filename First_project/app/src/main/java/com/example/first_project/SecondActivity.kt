package com.example.first_project

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class SecondActivity : AppCompatActivity() {
    val btnNext2: Button
        get() = findViewById(R.id.btn_next2)

    val resName: TextView
        get() = findViewById(R.id.res_name)

    val resGmail: TextView
        get() = findViewById(R.id.res_gmail)

    val resAge: TextView
        get() = findViewById(R.id.res_age)

    val etext_address:EditText
    get() = findViewById(R.id.etext_address)
    var user:User?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        user = intent.getParcelableExtra<User>("USER")
        if (user != null) {
            resName.text = """
                name=${user!!.name}
            """.trimIndent()

            resGmail.text = """
                email=${user!!.email}
            """.trimIndent()

            resAge.text="""
                age=${user!!.age}
            """.trimIndent()
        }

       btnNext2.setOnClickListener {

           var address = etext_address.text.toString().trim()

           user!!.address = address
           var intent = Intent(this, ShowActivity::class.java)

           intent.putExtra("USER", user)
           startActivity(intent)
       }

    }

}