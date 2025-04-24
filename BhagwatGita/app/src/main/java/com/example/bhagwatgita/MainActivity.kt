package com.example.bhagwatgita

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagwatgita.adapter.ChapterAdapter
import com.example.bhagwatgita.model.GitaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: GitaViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this).get(GitaViewModel::class.java)


        viewModel.chaptersLiveData.observe(this) { chapters ->
            // Yahan RecyclerView me data set karo
            recyclerView.adapter = ChapterAdapter(chapters){ selectedChapter ->
                Toast.makeText(this, "Clicked: ${selectedChapter.name}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ChapterDetailActivity::class.java)
                intent.putExtra("chapter", selectedChapter)
                startActivity(intent)
            }
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }

        viewModel.errorLiveData.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchChapters()
    }
}