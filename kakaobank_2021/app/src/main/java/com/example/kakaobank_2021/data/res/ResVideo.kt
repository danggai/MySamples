package com.example.kakaobank_2021.data.res

data class ResVideo (
    val documents: List<Video>,
    val meta: Meta
) {
    data class Video (
        val title: String,
        val url: String,
        val datetime: String,
        val play_time: Int,
        val thumbnail: String,
        val author: String
    )
}