package com.example.kakaobank_2021.ui.main;

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaobank_2021.R
import com.example.kakaobank_2021.data.local.SearchedListItem
import com.example.kakaobank_2021.ui.main.search.MainSearchAdapter
import com.example.kakaobank_2021.ui.main.storage.MainStorageAdapter

object MainBindingAdapter {
    @BindingAdapter(value = ["items", "viewModel"], requireAll = true)
    @JvmStatic fun bindItemList(
        view: RecyclerView,
        items: MutableList<SearchedListItem>,
        vm: MainViewModel
    ) {
        view.adapter?.run {
            if (this is MainSearchAdapter) {
                this.setItemList(items)
            }
        } ?: run {
            when (view.id) {
                R.id.rv_search_list -> {
                    MainSearchAdapter(vm).apply {
                        this.setHasStableIds(true)
                        view.adapter = this
                        this.setItemList(items)
                    }
                }
                R.id.rv_saved_list -> {
                    MainStorageAdapter(vm).apply {
                        this.setHasStableIds(true)
                        view.adapter = this
                        this.setItemList(items)
                    }
                }
                else -> {
                }
            }

        }
    }
}