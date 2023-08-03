package com.example.apnibookproject.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apnibookproject.ClientUpdateActivity
import com.example.apnibookproject.databinding.ClientCardViewBinding
import com.example.apnibookproject.dataclass.Client
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ClientListAdapter(var context: Context, var clientList: MutableList<Client>) :
    RecyclerView.Adapter<ClientListAdapter.MyViewHolder>() {

    lateinit var mRef: DatabaseReference
    lateinit var binding: ClientCardViewBinding

    class MyViewHolder(var bind: ClientCardViewBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ClientCardViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        mRef = FirebaseDatabase.getInstance().reference

        var client = clientList[position]

        holder.bind.tvCardName.text = client.name
        holder.bind.tvCardContact.text = client.contact

        holder.bind.clientCardView.setOnClickListener {
            var intent = Intent(context, ClientUpdateActivity::class.java)
            intent.putExtra("CLIENT", clientList[position])
            context.startActivity(intent)
        }

        holder.bind.clientCardView.setOnLongClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Delete")
                    .setMessage("you are delete the Client")
                    .setPositiveButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            mRef.child("Client-node").child(client.id).removeValue()
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {

                                    }
                                }
                        })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
            }.show()
            return@setOnLongClickListener true
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(clientList: MutableList<Client>) {
        this.clientList = clientList
        notifyDataSetChanged()
    }
}