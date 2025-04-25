package com.example.mixplayer.adpater

import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mixplayer.R
import java.io.File

class MediaAdapter(
    private val files: List<File>,
    private val onClick: (File) -> Unit
) : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {

    inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumb: ImageView = itemView.findViewById(R.id.thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val file = files[position]

        // Use thumbnail or icon (for audio/video/image)
//        holder.thumb.setImageResource(R.drawable.musicalnote) // change based on file type
//        holder.itemView.setOnClickListener { onClick(file) }

        when {
            isAudioFile(file) -> holder.thumb.setImageResource(R.drawable.musicalnote)
            isVideoFile(file) -> {
                Glide.with(holder.thumb.context)
                    .load(Uri.fromFile(file))
                    .thumbnail(0.1f)
                    .centerCrop()
                    .placeholder(R.drawable.playvideo)
                    .into(holder.thumb)
            }
            isImageFile(file) ->
                 Glide.with(holder.thumb.context)
                .load(Uri.fromFile(file))
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.thumb)

            else -> holder.thumb.setImageResource(R.drawable.ic_launcher_background)
        }

        holder.itemView.setOnClickListener {
            onClick(file)
        }
    }

    override fun getItemCount() = files.size

    fun isVideoFile(file: File): Boolean {
        val videoRegex = Regex(".*\\.(mp4|mkv|avi|3gp|webm|flv|mov)$", RegexOption.IGNORE_CASE)
        return videoRegex.matches(file.path)
    }

    fun isAudioFile(file: File): Boolean {
        val audioRegex = Regex(".*\\.(mp3|wav|aac|ogg|flac)$", RegexOption.IGNORE_CASE)
        return audioRegex.matches(file.path)
    }

    fun isImageFile(file: File): Boolean {
        val imageRegex = Regex(".*\\.(jpg|jpeg|png|gif|bmp|webp)$", RegexOption.IGNORE_CASE)
        return imageRegex.matches(file.path)
    }
}
