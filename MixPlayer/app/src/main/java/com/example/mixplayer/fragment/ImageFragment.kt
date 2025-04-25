package com.example.mixplayer.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mixplayer.ImageViewerActivity
import com.example.mixplayer.R
import com.example.mixplayer.adpater.MediaAdapter
import com.example.mixplayer.other.FileFetcher

class ImageFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MediaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        recyclerView = view.findViewById(R.id.recyclerImage)
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        val imageList = FileFetcher.getImageFiles(requireContext())
        adapter = MediaAdapter(imageList) { file ->
            val intent = Intent(requireContext(), ImageViewerActivity::class.java)
            intent.putExtra("path", file.path)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        return view
    }
}