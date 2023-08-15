package com.example.validation.recycler_task

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.validation.R
import com.example.validation.recycler_task.adapter.CustomAdapter
import com.example.validation.recycler_task.model.DataItem
import com.example.validation.recycler_task.model.OnDeleteClickListener

class HomeActivity : AppCompatActivity(), OnDeleteClickListener {
    private val dataList = mutableListOf<DataItem>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CustomAdapter(dataList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val openDialogButton = findViewById<Button>(R.id.openDialogButton)
        openDialogButton.setOnClickListener {
            openCustomDialog()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun openCustomDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)

        val editText1 = dialog.findViewById<EditText>(R.id.editText1)
        val editText2 = dialog.findViewById<EditText>(R.id.editText2)
        val submitButton = dialog.findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val text1 = editText1.text.toString()
            val text2 = editText2.text.toString()

            if (text1.isNotEmpty() && text2.isNotEmpty()) {
                dataList.add(DataItem(text1, text2))
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    override fun onDeleteClick(position: Int) {
        dataList.removeAt(position)
        adapter.notifyDataSetChanged()
    }
}