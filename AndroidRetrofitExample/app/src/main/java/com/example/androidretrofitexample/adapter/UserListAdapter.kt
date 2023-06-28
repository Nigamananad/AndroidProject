package com.example.androidretrofitexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidretrofitexample.data.Data
import com.example.androidretrofitexample.data.MainResponse
import com.example.androidretrofitexample.databinding.UserListLayoutBinding


class UserListAdapter(var context: Context, var userList: MutableList<Data>) :
    RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    lateinit var binding: UserListLayoutBinding

    class MyViewHolder(var bind: UserListLayoutBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = UserListLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]
        holder.bind.tvName.text = user.first_name
        holder.bind.tvEmail.text = user.email


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setItem(userList: MutableList<Data>){
        this.userList = userList
        notifyDataSetChanged()
    }

}