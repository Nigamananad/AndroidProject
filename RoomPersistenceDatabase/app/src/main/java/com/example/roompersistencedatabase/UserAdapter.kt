package com.example.roompersistencedatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.roompersistencedatabase.databinding.LayoutListviewBinding
import com.example.roompersistencedatabase.data.User

class UserAdapter(var context: Context, var userList:MutableList<User>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    lateinit var binding: LayoutListviewBinding
    lateinit var listener:OnItemClickListener
    class MyViewHolder(var bind:LayoutListviewBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = LayoutListviewBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]
        holder.bind.tvName.text = user.name
        holder.bind.tvEmail.text=user.email
        holder.bind.tvContact.text=user.contact
        holder.bind.tvAge.text=user.age

        holder.bind.userCardView.setOnClickListener {
            listener.onItemClicked(position,user)
            Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        }
    }
    interface OnItemClickListener{
        fun onItemClicked(index:Int,user:User)
    }
    fun setItems(muserlist:MutableList<User>)
    {
        this.userList=muserlist
        notifyDataSetChanged()
    }
    fun setOnItemClickListener(updateListener:OnItemClickListener)
    {
        this.listener=updateListener
    }
}