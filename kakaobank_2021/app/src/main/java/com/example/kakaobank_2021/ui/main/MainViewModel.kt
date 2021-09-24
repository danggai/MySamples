package com.example.kakaobank_2021.ui.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kakaobank_2021.data.api.ApiRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val api: ApiRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    fun Disposable.addCompositeDisposable() { compositeDisposable.add(this) }

    private val rxApiSearchImage: PublishSubject<Boolean> = PublishSubject.create()
    private val rxApiSearchVideo: PublishSubject<Boolean> = PublishSubject.create()

    val keyword = MutableLiveData<String>("")

    init {
        initRx()
    }

    private fun initRx() {
            rxApiSearchImage
                .observeOn(Schedulers.newThread())
                .filter {
                    it
                }
                .switchMap {
                    Log.d("search image", "start")
                    api.searchImage(keyword.value?:"")
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    Log.d("search image", res.toString())
                }, {
                    it.message?.let { msg -> Log.e("search image", msg) }
                }).addCompositeDisposable()

            rxApiSearchVideo
                .observeOn(Schedulers.newThread())
                .filter {
                    it
                }
                .switchMap {
                    Log.d("search video", "start")
                    api.searchVideo(keyword.value?:"")
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    Log.d("search video", res.toString())
                }, {
                    it.message?.let { msg -> Log.e("search video", msg) }
                }).addCompositeDisposable()
    }

    fun onClickSearch() {
        Log.d("onClick", "onClickSearch")
        Log.d("query", keyword.value?:"null")

        rxApiSearchImage.onNext(true)
        rxApiSearchVideo.onNext(true)
    }

}