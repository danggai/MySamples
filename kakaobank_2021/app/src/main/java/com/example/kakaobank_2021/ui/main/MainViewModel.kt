package com.example.kakaobank_2021.ui.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kakaobank_2021.data.api.ApiRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val api: ApiRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val rxApiSearchImage: PublishSubject<Boolean> = PublishSubject.create()
    private val rxApiSearchVideo: PublishSubject<Boolean> = PublishSubject.create()

    val keyword = MutableLiveData<String>("")

    init {
        initRx()
    }

    private fun initRx() {
        compositeDisposable.addAll(
            rxApiSearchImage
                .observeOn(Schedulers.newThread())
                .filter { it }
                .switchMap {
                    Log.d("", "")
                    api.searchImage(keyword.value?:"")
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    Log.d("", res.toString())
                }, {
                    it.message?.let { msg -> Log.e("", msg) }
                })
            ,
            rxApiSearchVideo
                .observeOn(Schedulers.newThread())
                .filter { it }
                .switchMap {
                    Log.d("", "")
                    api.searchVideo(keyword.value?:"")
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    Log.d("", res.toString())
                }, {
                    it.message?.let { msg -> Log.e("", msg) }
                })
        )
    }

    fun onClickSearch() {
        Log.d("onClick", "onClickSearch")
        rxApiSearchImage.onNext(true)
        rxApiSearchVideo.onNext(true)
    }

}