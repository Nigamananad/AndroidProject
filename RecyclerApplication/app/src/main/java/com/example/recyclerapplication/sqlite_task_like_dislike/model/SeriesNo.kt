package com.example.recyclerapplication.sqlite_task_like_dislike.model

data class SeriesNo(
    var id: Int,
    val seriesNo: String,
    var likeStatus: Int,
    var disLikeStatus: Int
)
