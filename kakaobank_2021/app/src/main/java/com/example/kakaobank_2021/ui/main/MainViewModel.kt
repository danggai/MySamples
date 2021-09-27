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
import kr.danal.app.damoum.core.Event

class MainViewModel(private val api: ApiRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private fun Disposable.addCompositeDisposable() { compositeDisposable.add(this) }

    private val rxApiSearchImage: PublishSubject<Boolean> = PublishSubject.create()
    private val rxApiSearchVideo: PublishSubject<Boolean> = PublishSubject.create()

    val keyword: MutableLiveData<String> = MutableLiveData<String>("카카오")

    var lvItemSetChanged: MutableLiveData<Boolean> = MutableLiveData()

    var searchedList: NonNullMutableLiveData<MutableList<SearchedListItem>> = NonNullMutableLiveData(mutableListOf())
    var savedList: NonNullMutableLiveData<MutableList<SearchedListItem>> = NonNullMutableLiveData(mutableListOf())

    var lvEventDataSetChanged = MutableLiveData<Event<Boolean>>()
    var lvEventSaveItem = MutableLiveData<Event<Int>>()

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
                    Log.d("search image", "done")
                    val list = mutableListOf<SearchedListItem>()

                    for (item in res.documents) {
                        list.add(SearchedListItem(item.display_sitename, item.thumbnail_url, "I", item.datetime))
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
                    val list = mutableListOf<SearchedListItem>()

                    for (item in res.documents) {
                        list.add(SearchedListItem(item.title, item.thumbnail, "V", item.datetime))
                    }
                    searchedList.value = (searchedList.value + list) as MutableList<SearchedListItem>

                }, {
                    it.message?.let { msg -> Log.e("search video", msg) }
                }).addCompositeDisposable()
    }

    fun onClickItem(item: SearchedListItem) {
        Log.d("onClick", "item")

        if (item in savedList.value) {
            savedList.value = (savedList.value - item) as MutableList<SearchedListItem>
        } else {
            savedList.value = (savedList.value + item) as MutableList<SearchedListItem>
        }

        lvEventSaveItem.value = Event(searchedList.value.indexOf(item))
    }

    fun onClickSearch() {
        Log.d("onClick", "onClickSearch")
        searchedList.value.clear()

        rxApiSearchImage.onNext(true)
        rxApiSearchVideo.onNext(true)

        lvEventDataSetChanged.value = Event(true)
    }
}