package com.example.likemate

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.likemate.adapter.NumberAdapter
import com.example.likemate.database.AppDatabase
import com.example.likemate.databinding.ActivityLikedNumbersBinding
import com.example.likemate.viewmodel.NumberViewModel
import com.example.likemate.viewmodel.NumberViewModelFactory

class LikedNumbersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLikedNumbersBinding
    private lateinit var adapter: NumberAdapter
    private val viewModel: NumberViewModel by viewModels {
        NumberViewModelFactory(AppDatabase.getDatabase(this).numberDao())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikedNumbersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NumberAdapter(viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // ✅ Sirf liked numbers fetch karna
        viewModel.getLikedNumbers().observe(this) { numbers ->
            adapter.submitList(numbers)
        }

    }
}