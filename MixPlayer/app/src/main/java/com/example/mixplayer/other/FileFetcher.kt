package com.example.mixplayer.other

import android.content.Context
import android.provider.MediaStore
import java.io.File

object FileFetcher {
    fun getAudioFiles(context: Context): List<File> {
        val audioList = mutableListOf<File>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Audio.Media.DATA)

        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val dataIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            while (it.moveToNext()) {
                val path = it.getString(dataIndex)
                audioList.add(File(path))
            }
        }

        return audioList
    }

    // Similar methods for getVideoFiles() and getImageFiles()


    fun getVideoFiles(context: Context): List<File> {
        val videoList = mutableListOf<File>()
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Video.Media.DATA)

        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val dataIndex = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            while (it.moveToNext()) {
                val path = it.getString(dataIndex)
                videoList.add(File(path))
            }
        }

        return videoList
    }

    fun getImageFiles(context: Context): List<File> {
        val imageList = mutableListOf<File>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.DATA)

        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val dataIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            while (it.moveToNext()) {
                val path = it.getString(dataIndex)
                imageList.add(File(path))
            }
        }

        return imageList
    }
}
