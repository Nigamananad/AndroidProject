package com.example.login_and_signup.adapter

import android.content.Context
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_and_signup.databinding.UserListLayoutBinding
import com.example.login_and_signup.model.User

class UserListAdapter(var context: Context, var userList: MutableList<User>) :
    RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    lateinit var binding: UserListLayoutBinding

    class MyViewHolder(var bind: UserListLayoutBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = UserListLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]
        holder.bind.tvName.text = "${user.name}"
        holder.bind.tvEmail.text = "${user.email}"
        holder.bind.tvContact.text = "${user.Contact}"
    }

}