package com.example.demoapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.demoapplication.R

class CustomerAdapter(context: Context, private val items: Array<String>) :
    ArrayAdapter<String>(context, 0, items) {

    private var editClickListener: ((Int) -> Unit)? = null
    private var deleteClickListener: ((Int) -> Unit)? = null


    fun setOnEditClickListener(listener: (Int) -> Unit) {
        editClickListener = listener
    }

    fun setOnDeleteClickListener(listener: (Int) -> Unit) {
        deleteClickListener = listener
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val currentItem = items[position]

        val itemImage = itemView?.findViewById<ImageView>(R.id.itemImage)
        val itemText = itemView?.findViewById<TextView>(R.id.itemText)
        val editButton = itemView?.findViewById<ImageButton>(R.id.editButton)
        val deleteButton = itemView?.findViewById<ImageButton>(R.id.deleteButton)

        // आपके आवश्यकताओं के अनुसार आइटम दिखाने के कोड यहाँ लिखें

        // संपादन बटन के क्लिक घटना का हैंडलिंग
        editButton?.setOnClickListener {
            editClickListener?.invoke(position)
        }

        // हटाने बटन के क्लिक घटना का हैंडलिंग
        deleteButton?.setOnClickListener {
            deleteClickListener?.invoke(position)
        }

        return itemView!!
    }

}