package com.example.recyclerapplication.sqlite_task_like_dislike.model

interface OnItemInteractionListener {
    fun onLikeClicked(item: SeriesNo)
    fun onDislikeClicked(item: SeriesNo)
}