package com.example.kakaobank_2021.ui.main;

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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
            MainSearchAdapter(vm).apply {
                this.setHasStableIds(true)
                view.adapter = this
                this.setItemList(items)
            }
        }
    }

    @BindingAdapter(value = ["isChanged"], requireAll = true)
    @JvmStatic fun bindNotifyDataSetChanged(
        view: RecyclerView,
        isChanged: Boolean,
    ) {
        if (!isChanged) return
        view.adapter?.run {
            if (this is MainSearchAdapter) {
                this.notifyDataSetChanged()
            }
            else if (this is MainStorageAdapter) {
                this.notifyDataSetChanged()
            }
        }
    }
}