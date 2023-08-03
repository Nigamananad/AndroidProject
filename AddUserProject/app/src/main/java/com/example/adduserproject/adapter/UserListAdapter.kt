package com.example.adduserproject.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.adduserproject.UpdateActivity
import com.example.adduserproject.databinding.UserCardViewBinding
import com.example.adduserproject.model.AppDatabase
import com.example.adduserproject.model.User

class UserListAdapter(var context: Context, var userList: MutableList<User>) :
    RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    lateinit var db: AppDatabase
    lateinit var binding: UserCardViewBinding

    class MyViewHolder(var bind: UserCardViewBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = UserCardViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]
        holder.bind.tvName.text = user.name
        holder.bind.tvDepartment.text = user.department

        holder.bind.cardView.setOnClickListener {
            var intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("DATA", user)
            context.startActivity(intent)
        }
        holder.bind.cardView.setOnLongClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Delete")
                    .setMessage("You Are Delete the User")
                    .setPositiveButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            db = Room.databaseBuilder(
                                context,
                                AppDatabase::class.java,
                                name = "nnb.db"
                            )
                                .allowMainThreadQueries().build()
                            db.userdao().deleteUser(user)
                            notifyItemRemoved(position)
                            userList.removeAt(index = position)
                        })
                    .setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->  })
            }.show()
            return@setOnLongClickListener true
        }
    }

    fun setItem(userList: MutableList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

}