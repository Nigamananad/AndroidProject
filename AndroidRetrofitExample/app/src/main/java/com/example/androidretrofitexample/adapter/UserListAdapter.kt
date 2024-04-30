package com.example.androidretrofitexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidretrofitexample.data.Data
import com.example.androidretrofitexample.data.MainResponse
import com.example.androidretrofitexample.databinding.UserListLayoutBinding


class UserListAdapter(var context: Context, var userList: MutableList<Data>) :
    RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    lateinit var binding: UserListLayoutBinding
    private val itemSelectedList = mutableListOf<Int>()

    class MyViewHolder(var bind: UserListLayoutBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = UserListLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]
        holder.bind.tvName.text = user.first_name
        holder.bind.tvEmail.text = user.email


        holder.bind.cardItem.setOnLongClickListener {
            selectItem(holder, user, position)
            true
        }

    }

    private fun selectItem(holder: UserListAdapter.MyViewHolder, user: Data, position: Int) {
        itemSelectedList.add(position)
        user.selected = true
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    fun setItem(userList: MutableList<Data>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}