package com.example.apnibookproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apnibookproject.databinding.ActivityAddClientBinding
import com.example.apnibookproject.dataclass.Client
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddClientActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddClientBinding
    lateinit var mRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddClientBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mRef=FirebaseDatabase.getInstance().reference

        binding.submitClient.setOnClickListener {
            var clientname = binding.textClientName.text.toString().trim()
            var clientcontact = binding.textClientContact.text.toString().trim()


            addClient(clientname, clientcontact)
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun addClient(clientname: String, clientcontact: String) {

        var id = mRef.push().key
        if (id != null) {
            var client = Client(id, clientname, clientcontact)
            mRef.child("Client-node").child(client.id).setValue(client).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "Client Added", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}