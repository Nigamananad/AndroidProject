package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.demoapplication.adapter.CustomerAdapter

class ListActivity : AppCompatActivity() {

    private val items = arrayOf("आइटम 1", "आइटम 2", "आइटम 3", "आइटम 4", "आइटम 5")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val listView = findViewById<ListView>(R.id.list_view)

        val adapter = CustomerAdapter(this, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            Toast.makeText(this, "चयनित आइटम: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        adapter.setOnEditClickListener { position ->
            // संपादित करने का कोड यहाँ लिखें
            // position आपके डेटा स्रोत में कौनसे आइटम का संपादन करना है यह बताता है
        }

        adapter.setOnDeleteClickListener { position ->
            // हटाने का कोड यहाँ लिखें
            // position आपके डेटा स्रोत में कौनसे आइटम को हटाना है यह बताता है
        }

    }
}