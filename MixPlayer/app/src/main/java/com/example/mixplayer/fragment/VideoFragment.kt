package com.example.mixplayer.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mixplayer.R
import com.example.mixplayer.VideoPlayerActivity
import com.example.mixplayer.adpater.MediaAdapter
import com.example.mixplayer.other.FileFetcher

class VideoFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MediaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        recyclerView = view.findViewById(R.id.recyclerVideo)
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        val videoList = FileFetcher.getVideoFiles(requireContext())
        adapter = MediaAdapter(videoList) { file ->
            val intent = Intent(requireContext(), VideoPlayerActivity::class.java)
            intent.putExtra("path", file.path)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        return view
    }
}