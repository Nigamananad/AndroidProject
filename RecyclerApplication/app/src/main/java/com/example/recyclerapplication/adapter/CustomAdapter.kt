package com.example.recyclerapplication.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerapplication.R
import com.example.recyclerapplication.model.OnDeleteClickListener
import com.example.recyclerapplication.model.OnUpdateClickListener
import com.example.recyclerapplication.model.YourDataItem

class CustomAdapter(
    private var dataList: MutableList<YourDataItem>,
    private val onDeleteClickListener: OnDeleteClickListener,
    private val onUpdateClickListener: OnUpdateClickListener
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1TextView: TextView = itemView.findViewById(R.id.textView1)
        val text2TextView: TextView = itemView.findViewById(R.id.textView2)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        val updateButton: ImageButton = itemView.findViewById(R.id.updateButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]

        holder.text1TextView.text = "Text 1: ${currentItem.text1}"
        holder.text2TextView.text = "Text 2: ${currentItem.text2}"

        holder.deleteButton.setOnClickListener {
            onDeleteClickListener.onDeleteClick(position)
        }

        holder.updateButton.setOnClickListener {
//            onUpdateClickListener.onUpdateClick(position, currentItem.text1, currentItem.text2)
            showUpdateDialog(holder,position, currentItem)
        }
    }

    fun filterList(filteredList: List<YourDataItem>) {
        dataList.clear()
        dataList.addAll(filteredList)
        notifyDataSetChanged()
    }

    private fun showUpdateDialog(holder:ViewHolder,position: Int, currentItem: YourDataItem) {
        val dialog = Dialog(holder.itemView.context)
        dialog.setContentView(R.layout.update_dialog_layout)

        val editText1 = dialog.findViewById<EditText>(R.id.editText1)
        val editText2 = dialog.findViewById<EditText>(R.id.editText2)
        val submitButton = dialog.findViewById<Button>(R.id.submitButton)

        editText1.setText(currentItem.text1)
        editText2.setText(currentItem.text2)

        submitButton.setOnClickListener {
            val newText1 = editText1.text.toString()
            val newText2 = editText2.text.toString()

            onUpdateClickListener.onUpdateClick(position, newText1, newText2)
            dialog.dismiss()
        }

        dialog.show()

    }
}