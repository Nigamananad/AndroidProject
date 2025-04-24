package com.jndevelopstudio.nonetexplore.adapter.pixelpuzzle

import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView

class PuzzleImageAdapter(
    private val tileImages: List<View>,
    private val boardColumnWidth: Int,
    private val boardColumnHeight: Int
) : BaseAdapter() {

    override fun getCount(): Int = tileImages.size

    override fun getItem(position: Int): View = tileImages[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tileImageView = if (convertView == null) {
            tileImages[position]
        } else {
            convertView as View
        }
        tileImageView.layoutParams = AbsListView.LayoutParams(boardColumnWidth, boardColumnHeight)

        return tileImageView
    }
}