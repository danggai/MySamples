package com.example.kakaobank_2021.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kakaobank_2021.data.api.ApiRepository
import com.example.kakaobank_2021.data.local.SearchedListItem
import com.example.kakaobank_2021.util.NonNullMutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val api: ApiRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private fun Disposable.addCompositeDisposable() { compositeDisposable.add(this) }

    private val rxApiSearchImage: PublishSubject<Boolean> = PublishSubject.create()
    private val rxApiSearchVideo: PublishSubject<Boolean> = PublishSubject.create()

    val keyword: MutableLiveData<String> = MutableLiveData<String>("")

    var lvItemSetChanged: MutableLiveData<Boolean> = MutableLiveData(false)

    var searchedList: NonNullMutableLiveData<MutableList<SearchedListItem>> = NonNullMutableLiveData(mutableListOf())
    var savedList: NonNullMutableLiveData<MutableList<SearchedListItem>> = NonNullMutableLiveData(mutableListOf())

    init {
        initRx()

        searchedList.value = mutableListOf(SearchedListItem("ㅎㅇ", "https://t1.kakaocdn.net/kakaocorp/kakaocorp/admin/news/e2ae4c6b017b00001.jpg?type=thumb&opt=C630x472","I","저장 시간",false))
//        savedList.value = mutableListOf(SearchedListItem("ㅎㅇ", "https://t1.kakaocdn.net/kakaocorp/kakaocorp/admin/news/e2ae4c6b017b00001.jpg?type=thumb&opt=C630x472","I","저장 시간",false))
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
                    Log.d("search image", "done")

                    val list = mutableListOf<SearchedListItem>()

                    for (item in res.documents) {
                        list.add(SearchedListItem(item.display_sitename, item.thumbnail_url, "I", item.datetime, false))
                    }

                    searchedList.value = (searchedList.value + list) as MutableList<SearchedListItem>
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
                    Log.d("search video", "done")
//                    Log.d("search video", res.toString())
                    val list = mutableListOf<SearchedListItem>()

                    for (item in res.documents) {
                        list.add(SearchedListItem(item.title, item.thumbnail, "V", item.datetime, false))
                    }

                    searchedList.value = (searchedList.value + list) as MutableList<SearchedListItem>
                }, {
                    it.message?.let { msg -> Log.e("search video", msg) }
                }).addCompositeDisposable()
    }

    fun onClickItem(item: SearchedListItem) {
        if (savedList.value.contains(item)) {
            savedList.value.remove(item)
            item.is_saved = false
        } else {
            savedList.value.add(item)
            item.is_saved = true
        }

        lvItemSetChanged.value = true
    }

    fun onClickSearch() {
        Log.d("onClick", "onClickSearch")
        Log.d("query", keyword.value?:"null")
        searchedList.value.clear()

        rxApiSearchImage.onNext(true)
        rxApiSearchVideo.onNext(true)

        lvItemSetChanged.value = true
    }

}