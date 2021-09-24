package com.example.kakaobank_2021.data.res

data class ResImage (
    val documents: List<Image>,
    val meta: Meta
) {
    data class Image (
        val collection: String,
        val thumbnail_url: String,
        val image_url: String,
        val width: String,
        val height: String,
        val display_sitename: String,
        val doc_url: String,
        val datetime: String,
    )
}