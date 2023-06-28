package com.example.apnibookproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apnibookproject.databinding.ClientCardViewBinding
import com.example.apnibookproject.dataclass.Client

class ClientListAdapter(var context: Context, var clientList: MutableList<Client>) :
    RecyclerView.Adapter<ClientListAdapter.MyViewHolder>() {
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

        var client = clientList[position]

        holder.bind.tvCardName.text = "${client.name}"
        holder.bind.tvCardContact.text = "${client.contact}"

    }

    fun setItems(clientList:MutableList<Client>){
        this.clientList = clientList
        notifyDataSetChanged()
    }
}