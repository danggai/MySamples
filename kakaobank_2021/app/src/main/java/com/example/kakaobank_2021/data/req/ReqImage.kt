package com.example.kakaobank_2021.data.req


data class ReqImage (
    val query: String,
    val sort: String?,
    val page: Int?,
    val size: Int?
) {
}