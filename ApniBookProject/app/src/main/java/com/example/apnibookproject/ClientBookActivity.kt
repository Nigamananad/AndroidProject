package com.example.apnibookproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apnibookproject.Adapter.ClientListAdapter
import com.example.apnibookproject.databinding.ActivityClientBookBinding
import com.example.apnibookproject.dataclass.Client
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ClientBookActivity : AppCompatActivity() {
    lateinit var binding: ActivityClientBookBinding
    lateinit var mRef: DatabaseReference

    var clientList = mutableListOf<Client>()
    lateinit var clientAdapter: ClientListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mRef = FirebaseDatabase.getInstance().reference

        binding.addClient.setOnClickListener {
            startActivity(Intent(this, AddClientActivity::class.java))
        }


        clientAdapter = ClientListAdapter(this, clientList)
        binding.recyclerClient.layoutManager = LinearLayoutManager(this)
        binding.recyclerClient.adapter = clientAdapter

        loadClientList()
    }

    private fun loadClientList() {

        mRef.child("Client-node").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                clientList.clear()

                for (snap in snapshot.children) {
                    var client = snap.getValue(Client::class.java)
                    if (client != null) {
                        clientList.add(client)
                    }
                }
                clientAdapter.setItems(clientList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}