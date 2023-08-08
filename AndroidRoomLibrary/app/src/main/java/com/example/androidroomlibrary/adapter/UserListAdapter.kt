package com.example.androidroomlibrary.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.androidroomlibrary.R
import com.example.androidroomlibrary.UpdateActivity
import com.example.androidroomlibrary.database.AppDatabase
import com.example.androidroomlibrary.databinding.CardViewBinding
import com.example.androidroomlibrary.model.User

class UserListAdapter(var context: Context, var userList: MutableList<User>) :
    RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    lateinit var db: AppDatabase
//    lateinit var binding: CardViewBinding

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        var deletebtn = itemView.findViewById<Button>(R.id.btn_delete)
        val editbtn = itemView.findViewById<Button>(R.id.btn_edit)
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
//        val cardView = itemView.findViewById<CardView>(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        binding = CardViewBinding.inflate(LayoutInflater.from(context), parent, false)
//        return MyViewHolder(binding)
        val itemView = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var user = userList[position]
        holder.tvName.text = user.name
        holder.tvDescription.text = user.description

        holder.deletebtn.setOnLongClickListener {

            AlertDialog.Builder(context).apply {
                setTitle("Delete")
                    .setMessage("you are delete the item")
                    .setPositiveButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            db = Room.databaseBuilder(
                                context,
                                AppDatabase::class.java,
                                name = "dixit.db"
                            ).allowMainThreadQueries().build()
                            db.userdao().deleteuser(user)
                            notifyItemRemoved(position)
                            userList.removeAt(position)
                        })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
            }.show()
            return@setOnLongClickListener true
        }

        holder.editbtn.setOnClickListener {
            var intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("USER", user)
            context.startActivity(intent)
        }
    }

    fun setitem(userList: MutableList<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }


}