package com.example.recyclerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.adapter.CustomAdapter
import com.example.recyclerapplication.model.OnDeleteClickListener
import com.example.recyclerapplication.model.OnUpdateClickListener
import com.example.recyclerapplication.model.SharedPreferenceUtil
import com.example.recyclerapplication.model.YourDataItem

class MainActivity : AppCompatActivity(), OnDeleteClickListener, OnUpdateClickListener {
    private var dataList = mutableListOf<YourDataItem>()
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferenceUtil = SharedPreferenceUtil(this)
        dataList = sharedPreferenceUtil.getDataList().toMutableList()

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CustomAdapter(dataList, this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val text1 = findViewById<EditText>(R.id.editText1).text.toString()
            val text2 = findViewById<EditText>(R.id.editText2).text.toString()

            if (text1.isNotEmpty() && text2.isNotEmpty()) {
                dataList.add(YourDataItem(text1, text2))
                adapter.notifyDataSetChanged()
                sharedPreferenceUtil.saveDataList(dataList)
            }
        }
    }

    override fun onDeleteClick(position: Int) {
        dataList.removeAt(position)
        adapter.notifyDataSetChanged()
        sharedPreferenceUtil.saveDataList(dataList)
    }

    override fun onUpdateClick(position: Int, newText1: String, newText2: String) {
        dataList[position] = YourDataItem(newText1, newText2)
        adapter.notifyDataSetChanged()
        sharedPreferenceUtil.saveDataList(dataList)
    }
}