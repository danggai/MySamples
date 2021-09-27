package com.example.kakaobank_2021.data.local

data class SearchedListItem (
    val name: String,
    val thumbnail: String,
    val type: String,           // "V": video, "I": image
    val datetime: String,
) {
}