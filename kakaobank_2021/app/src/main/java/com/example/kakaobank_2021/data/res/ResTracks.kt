package com.example.kakaobank_2021.data.res

data class ResVideo (
    val meta: Meta,
    val data: List<Video>
) {
    data class Video (
        val datetime: String,
        val thumbnail: String
    )
}