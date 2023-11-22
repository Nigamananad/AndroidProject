package com.example.fetchfromfile.fetch_from_file

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchfromfile.R
import com.example.fetchfromfile.fetch_from_file.adapter.AudioListAdapter
import com.example.fetchfromfile.fetch_from_file.model.AudioFile
import java.io.File

class AudioListActivity : AppCompatActivity() {
   val audioList=ArrayList<String>()
    private lateinit var audioAdapter: AudioListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_list)

        val audioFiles = fetchAudioFiles()
        audioAdapter = AudioListAdapter(this,audioFiles)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_audio_item)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = audioAdapter
    }

    private fun fetchAudioFiles(): List<AudioFile> {
        val audioFiles = mutableListOf<AudioFile>()
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projection = arrayOf(
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DATA
        )
        val sortOrder = "${MediaStore.Audio.Media.TITLE} .mp3"

        val cursor = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )
        cursor?.use {
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val pathColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            while (it.moveToNext()) {
                val title = it.getString(titleColumn)
                val path = it.getString(pathColumn)
                audioFiles.add(AudioFile(title, path))
            }
        }
        return audioFiles
    }
}