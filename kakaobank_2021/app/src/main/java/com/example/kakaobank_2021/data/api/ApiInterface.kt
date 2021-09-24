package com.example.kakaobank_2021.data.api

import com.example.kakaobank_2021.data.res.ResImage
import com.example.kakaobank_2021.data.res.ResVideo
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("v2/search/image")
    fun searchImage(@Header("Authorization") header: String,
                    @Query("query") query: String,
                    @Query("sort") sort: String?
    ): Observable<Response<ResImage>>

    @GET("v2/search/vclip")
    fun searchVideo(@Header("Authorization") header: String,
                    @Query("query") query: String,
                    @Query("sort") sort: String?
    ): Observable<Response<ResVideo>>
}