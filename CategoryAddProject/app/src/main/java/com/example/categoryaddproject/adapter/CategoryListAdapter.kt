package com.example.categoryaddproject.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.categoryaddproject.AddProductActivity
import com.example.categoryaddproject.UpdateCategoryActivity
import com.example.categoryaddproject.database.AppDatabase
import com.example.categoryaddproject.databinding.CategoryCardViewBinding
import com.example.categoryaddproject.model.Category

class CategoryListAdapter(var context: Context, var categoryList: MutableList<Category>) :
    RecyclerView.Adapter<CategoryListAdapter.MyViewHolder>() {
    lateinit var db: AppDatabase
    lateinit var binding: CategoryCardViewBinding

    class MyViewHolder(var bind: CategoryCardViewBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CategoryCardViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var mCate = categoryList[position]
        holder.bind.tvCategory.text = mCate.name
        holder.bind.tvDescription.text = mCate.description

        holder.bind.categoryCardView.setOnLongClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Delete")
                    .setMessage("you are delete the item")
                    .setPositiveButton(
                        "Yes",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            db = Room.databaseBuilder(
                                context,
                                AppDatabase::class.java,
                                name = "category.db"
                            )
                                .allowMainThreadQueries().build()

                            db.categorydao().deleteCategory(mCate)
                            notifyItemRemoved(position)
                            categoryList.removeAt(position)
                        })
                    .setNegativeButton(
                        "No",
                        DialogInterface.OnClickListener { dialogInterface, i -> })
            }.show()
            return@setOnLongClickListener true
        }

        holder.bind.categoryCardView.setOnClickListener {
            var intent = Intent(context, UpdateCategoryActivity::class.java)
            intent.putExtra("DATA", mCate)
            context.startActivity(intent)
        }

    }

    fun setitem(categoryList: MutableList<Category>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }
}