package com.example.apnibookproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apnibookproject.databinding.ActivityAddStockBinding
import com.example.apnibookproject.dataclass.Stock
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddStockActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddStockBinding
    lateinit var mRef: DatabaseReference
    //lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddStockBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mRef = FirebaseDatabase.getInstance().reference

        binding.submitStock.setOnClickListener {
            var stockTittle = binding.textStockName.text.toString().trim()
            var stockQuantity = binding.textStockQuantity.text.toString().trim()

            addStock(stockTittle, stockQuantity)
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun addStock(stockTittle: String, stockQuantity: String) {

//        mAuth = FirebaseAuth.getInstance()
//        var  uid = mAuth.currentUser!!.uid
        var id = mRef.push().key
        if (id != null) {
            var stock = Stock(id, stockTittle, stockQuantity)
            mRef.child("Stock-node").child(stock.id).setValue(stock).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Stock Added", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}