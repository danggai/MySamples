package com.example.kakaobank_2021.data.api

import android.util.Log
import com.example.kakaobank_2021.data.res.Meta
import com.example.kakaobank_2021.data.res.ResImage
import com.example.kakaobank_2021.data.res.ResVideo
import io.reactivex.Observable

class ApiRepository(private val api: ApiInterface) {
    val api_key = "KakaoAK 6edc9797bd73ce2bba5d3e9e1132867d"

    fun searchImage(query: String): Observable<ResImage> {
        val emptyData = listOf<ResImage.Image>()
        return Observable.just(true)
            .switchMap {
                api.searchImage(api_key, query, "recency")
            }
            .map { res ->
                Log.d("searchImage", res.code().toString())
                when {
                    res.code() == 200 -> {
                        res.body()
                    } else -> {
                        ResImage(emptyData, Meta(0,0,true))
                    }
                }
            }
    }

    fun searchVideo(query: String): Observable<ResVideo> {
        val emptyData = listOf<ResVideo.Video>()
        return Observable.just(true)
            .switchMap {
                api.searchVideo(api_key, query, "recency")
            }
            .map { res ->
                Log.d("searchVideo", res.code().toString())
                when {
                    res.code() == 200 -> {
                        res.body()
                    } else -> {
                        ResVideo(emptyData, Meta(0,0,true))
                    }
                }
            }
    }
}