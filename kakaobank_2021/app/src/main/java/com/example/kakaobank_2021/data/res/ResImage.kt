package com.example.kakaobank_2021.data.res

data class ResImage (
    val meta: Meta,
    val data: List<Image>
) {
    data class Image (
        val datetime: String,
        val thumbnail_url: String
    )
}