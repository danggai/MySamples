package com.example.kakaobank_2021.data.api

import android.util.Log
import com.example.kakaobank_2021.data.res.Meta
import com.example.kakaobank_2021.data.res.ResImage
import com.example.kakaobank_2021.data.res.ResVideo
import io.reactivex.Observable

class ApiRepository(private val api: ApiInterface) {

    fun searchImage(keyword: String): Observable<ResImage> {
        val emptyData = listOf<ResImage.Image>()
        return Observable.just(true)
            .switchMap {
                api.searchImage(keyword)
            }
            .map { res ->
                Log.d("", res.body().toString())
                when {
                    res.isSuccessful -> {
                        ResImage(Meta(res.code(), res.message()), res.body()?:emptyData)
                    } else -> {
                        ResImage(Meta(res.code(), res.message()), emptyData)
                    }
                }
            }
    }

    fun searchVideo(keyword: String): Observable<ResVideo> {
        val emptyData = listOf<ResVideo.Video>()
        return Observable.just(true)
            .switchMap {
                api.searchVideo(keyword)
            }
            .map { res ->
                Log.d("", res.body().toString())
                when {
                    res.isSuccessful -> {
//                        res.body()?.let { data ->
//                            if (data.progresses.size > 1 &&
//                                data.progresses[0].time > data.progresses[data.progresses.lastIndex].time) {
//                                log.e()
//                                data.state.text = data.progresses[0].status.text
//                                data.progresses = data.progresses.reversed()
//                            }
//                        }
                        ResVideo(Meta(res.code(), res.message()), res.body()?:emptyData)
                    } else -> {
                        ResVideo(Meta(res.code(), res.message()), emptyData)
                    }
                }
            }
    }
}