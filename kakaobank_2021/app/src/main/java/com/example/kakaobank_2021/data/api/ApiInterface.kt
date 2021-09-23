package com.example.kakaobank_2021.data.api

import com.example.kakaobank_2021.data.res.ResImage
import com.example.kakaobank_2021.data.res.ResVideo
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("docs/latest/ko/daum-search/dev-guide#search-image")
    fun searchImage(keyword: String): Observable<Response<List<ResImage.Image>>>

    @GET("docs/latest/ko/daum-search/dev-guide#search-image")
    fun searchVideo(keyword: String): Observable<Response<List<ResVideo.Video>>>
}