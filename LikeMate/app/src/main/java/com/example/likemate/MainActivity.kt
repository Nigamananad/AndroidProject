package com.example.likemate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.likemate.adapter.NumberAdapter
import com.example.likemate.database.AppDatabase
import com.example.likemate.databinding.ActivityMainBinding
import com.example.likemate.model.NumberEntity
import com.example.likemate.viewmodel.NumberViewModel
import com.example.likemate.viewmodel.NumberViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //    private val viewModel: NumberViewModel by viewModels()
    private val viewModel: NumberViewModel by viewModels {
        NumberViewModelFactory(AppDatabase.getDatabase(this).numberDao())
    }
    private lateinit var adapter: NumberAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ RecyclerView Setup
        adapter = NumberAdapter(viewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter


        // ✅ LiveData Observer: Database Changes ko UI pe reflect karega
        viewModel.numbers.observe(this) { numbers ->
            adapter.submitList(numbers)
        }

        // ✅ Submit Button Click: Database me number add karega
        binding.btnSubmit.setOnClickListener {
            val numberText = binding.editTextNumber.text.toString()
            if (numberText.isNotEmpty()) {
                try {
                    val number = NumberEntity(number = numberText.toLong(), isLiked = false, isDisliked = false)  // 🔹 toInt() ko toLong() se replace kiya
                    viewModel.insertNumber(number)
                    binding.editTextNumber.text.clear() // Input field clear karna
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnLikedNumbers.setOnClickListener {
            val intent = Intent(this, LikedNumbersActivity::class.java)
            startActivity(intent)
        }

        binding.btnDislikedNumbers.setOnClickListener {
            val intent = Intent(this, DislikedNumbersActivity::class.java)
            startActivity(intent)
        }
    }
}