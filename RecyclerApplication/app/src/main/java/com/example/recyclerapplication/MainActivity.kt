package com.example.recyclerapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.adapter.CustomAdapter
import com.example.recyclerapplication.model.OnDeleteClickListener
import com.example.recyclerapplication.model.OnUpdateClickListener
import com.example.recyclerapplication.model.SharedPreferenceUtil
import com.example.recyclerapplication.model.YourDataItem
import java.util.Locale.filter

class MainActivity : AppCompatActivity(), OnDeleteClickListener, OnUpdateClickListener {
    private var dataList = mutableListOf<YourDataItem>()
    private var originalDataList = mutableListOf<YourDataItem>()
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferenceUtil = SharedPreferenceUtil(this)
        originalDataList = sharedPreferenceUtil.getDataList().toMutableList()
        dataList = originalDataList.toMutableList()

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CustomAdapter(dataList, this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })


        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val text1 = findViewById<EditText>(R.id.editText1).text.toString()
            val text2 = findViewById<EditText>(R.id.editText2).text.toString()

            if (text1.isNotEmpty() && text2.isNotEmpty()) {
                dataList.add(YourDataItem(text1, text2))
                originalDataList.add(YourDataItem(text1, text2))
                adapter.notifyDataSetChanged()
                sharedPreferenceUtil.saveDataList(dataList)
            }

        }
    }

    //    private fun filter(newText: String?) {
//
//        val filteredList = dataList.filter { item ->
//            item.text1.lowercase().contains(newText?.lowercase() ?: "")
//        }
//        adapter.filterList(filteredList)
//
//    }
    private fun filter(msg: String?) {
        val filteredList: ArrayList<YourDataItem> = ArrayList()
        for (item in originalDataList) {
            if (item.text1.contains(msg!!, ignoreCase = true) || item.text2.contains(
                    msg,
                    ignoreCase = true
                )
            ) {
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            recyclerView.isVisible = false
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredList)
            recyclerView.isVisible = true
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