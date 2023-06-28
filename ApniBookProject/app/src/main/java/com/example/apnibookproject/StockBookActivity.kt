package com.example.apnibookproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apnibookproject.Adapter.ClientListAdapter
import com.example.apnibookproject.Adapter.StockListAdapter
import com.example.apnibookproject.databinding.ActivityStockBookBinding
import com.example.apnibookproject.dataclass.Client
import com.example.apnibookproject.dataclass.Stock
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StockBookActivity : AppCompatActivity() {
    lateinit var binding: ActivityStockBookBinding
    lateinit var mRef: DatabaseReference
    var stockList = mutableListOf<Stock>()
    lateinit var stockAdapter: StockListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStockBookBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mRef = FirebaseDatabase.getInstance().reference

        binding.addStock.setOnClickListener {
            startActivity(Intent(this, AddStockActivity::class.java))
        }

        stockAdapter = StockListAdapter(this, stockList)
        binding.recyclerStock.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerStock.adapter = stockAdapter

        loadStockList()
    }

    private fun loadStockList() {
        mRef.child("Stock-node").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                stockList.clear()

                for (snap in snapshot.children) {
                    var stock = snap.getValue(Stock::class.java)
                    if (stock != null) {
                        stockList.add(stock)
                    }
                }
                stockAdapter.setItems(stockList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}