package com.example.datafetchfromfile

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.File

class ImageAdapter(private val imagePaths: MutableList<String>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imagePath = imagePaths[position]
        Glide.with(holder.itemView)
            .load(imagePath)
            .centerCrop()
            .into(holder.imageView)

        holder.btnDelete.setOnClickListener {
            val deletedImagePath = imagePaths[position]
            imagePaths.removeAt(position)
            notifyDataSetChanged()

            // Delete image from storage
            deleteImageFromStorage(holder.itemView.context, deletedImagePath)
        }
    }

    override fun getItemCount(): Int {
        return imagePaths.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete)
    }
//    private fun deleteImageFromStorage(context: Context, imagePath: String) {
//        // Convert image URI to file path
//        val uri = Uri.parse(imagePath)
//        val filePath = uri.path // Directly getting file path from URI
//
//        // Delete image file
//        val file = File(filePath)
//        if (file.exists()) {
//            file.delete()
//        }
//    }
private fun deleteImageFromStorage(context: Context, imagePath: String) {
    Log.d("ImageAdapter", "deleteImageFromStorage:  $imagePath")
    try {
        // Convert image URI to file path

            // Delete image file
            val file = File(imagePath)
        Log.d("ImageAdapter", "deleteImageFromStorage: $file")
            if (file.exists()) {
                val deleted = file.delete()
                if (deleted) {
                    Log.d("ImageAdapter", "File deleted successfully: $file")
                } else {
                    Log.e("ImageAdapter", "Failed to delete file: $file")
                }
            } else {
                Log.e("ImageAdapter", "File does not exist: $file")
            }

    } catch (e: Exception) {
        Log.e("ImageAdapter", "Error deleting file: ${e.message}")
        e.printStackTrace()
    }
}

}