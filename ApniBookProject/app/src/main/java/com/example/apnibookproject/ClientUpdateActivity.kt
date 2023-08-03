package com.example.apnibookproject

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apnibookproject.databinding.ActivityUpdateBinding
import com.example.apnibookproject.dataclass.Client
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ClientUpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var mRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mRef= FirebaseDatabase.getInstance().reference

        var client = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("CLIENT", Client::class.java)
        } else {
            intent.getParcelableExtra("CLIENT")
        }

        if (client != null) {
            binding.updateClientName.setText(client.name)
            binding.updateClientContact.setText(client.contact)
        }

        binding.btnUpdateClient.setOnClickListener {
            var name = binding.updateClientName.text.toString().trim()
            var contact = binding.updateClientContact.text.toString().trim()

            var mClient = Client(name = name, contact = contact, id = client!!.id)

            mRef.child("Client-node").child(mClient.id).setValue(mClient).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Update Successfully", Toast.LENGTH_SHORT).show()
                }
            }
            onBackPressedDispatcher.onBackPressed()
        }
    }
}