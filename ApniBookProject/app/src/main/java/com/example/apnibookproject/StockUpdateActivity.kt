package com.example.apnibookproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apnibookproject.databinding.ActivityStockUpdateBinding
import com.example.apnibookproject.dataclass.Stock
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StockUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityStockUpdateBinding
    lateinit var mRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStockUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mRef = FirebaseDatabase.getInstance().reference

        var stock = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("STOCK", Stock::class.java)
        } else {
            intent.getParcelableExtra("STOCK")
        }

        if (stock != null) {
            binding.updateStockName.setText(stock.tittle)
            binding.updateStockQuantity.setText(stock.quantity)
        }
        binding.btnUpdateStock.setOnClickListener {
            var tittle = binding.updateStockName.text.toString().trim()
            var quantity = binding.updateStockQuantity.text.toString().trim()

            var mStock = Stock(tittle = tittle, quantity = quantity, id = stock!!.id)

            mRef.child("Stock-node").child(mStock.id).setValue(mStock).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Update Successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            onBackPressedDispatcher.onBackPressed()
        }

    }
}