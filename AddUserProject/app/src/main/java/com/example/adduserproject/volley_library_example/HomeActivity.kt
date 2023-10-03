package com.example.adduserproject.volley_library_example

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.adduserproject.R
import com.example.adduserproject.volley_library_example.adapter.CourseRVAdapter
import com.example.adduserproject.volley_library_example.model.CourseRVModal

class HomeActivity : AppCompatActivity() {
    lateinit var courseRV: RecyclerView
    lateinit var loadingPB: ProgressBar
    lateinit var courseRVAdapter: CourseRVAdapter
    lateinit var courseList: ArrayList<CourseRVModal>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        courseRV = findViewById(R.id.idRVCourses)
        loadingPB = findViewById(R.id.idPBLoading)
        courseList = ArrayList()
        courseRVAdapter = CourseRVAdapter(courseList,this)
        courseRV.adapter = courseRVAdapter
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        val url = "https://www.jsonkeeper.com/b/0RH6"
        val queue = Volley.newRequestQueue(this)

        val request =
            JsonArrayRequest(Request.Method.GET, url, null, { response ->
                loadingPB.visibility = View.GONE
                try {
                    for (i in 0 until response.length()) {
                        val respObj = response.getJSONObject(i)
                        val lngName = respObj.getString("languageName")
                        val lngImg = respObj.getString("languageImg")

                        courseList.add(CourseRVModal(lngName, lngImg))

                        courseRVAdapter.notifyDataSetChanged()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }, { error ->
                Log.d("ERROR", "${error.message}")
                Toast.makeText(this, "Fail to get response", Toast.LENGTH_SHORT)
                    .show()
            })

        queue.add(request)
    }
}