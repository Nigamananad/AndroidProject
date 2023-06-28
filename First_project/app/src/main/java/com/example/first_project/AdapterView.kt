package com.example.first_project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.first_project.databinding.ActivityAdapterViewBinding

class AdapterView : AppCompatActivity() {

    lateinit var binding: ActivityAdapterViewBinding
    lateinit var mAdapter: ArrayAdapter<String>
    lateinit var mAdapterGrid: ArrayAdapter<String>

    var languageList = mutableListOf<String>()

    var gridList = mutableListOf<String>()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdapterViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        languageList.add("Profile")
        languageList.add("Java")
        languageList.add("Kotlin")
        languageList.add("PHP")
        languageList.add("C++")
        languageList.add("C Language")

        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, languageList)
        binding.lvItem.adapter = mAdapter

        binding.lvItem.setOnItemClickListener { adapterView, view, index, l ->


            var intent = Intent(this, DemoActivity::class.java)
            startActivity(intent)

        }

        gridList.add("odisha")
        gridList.add("gujarat")
        gridList.add("Asam")
        gridList.add("delhi")
        gridList.add("panjab")

        mAdapterGrid = ArrayAdapter(this, android.R.layout.simple_list_item_1, gridList)
        binding.gvItem.adapter = mAdapterGrid

        binding.gvItem.setOnItemClickListener { adapterView, view, index, l ->
            var language = gridList[index]
            Toast.makeText(this, "${language}", Toast.LENGTH_SHORT).show()

        var intent=Intent(this,ShowActivity ::class.java)
            intent.putExtra("LANG" ,language)
            startActivity(intent)


        }

    }

}