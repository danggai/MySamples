package com.example.kakaobank_2021.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.kakaobank_2021.R
import com.example.kakaobank_2021.ui.main.MainViewModel


class MainSearchAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<MainSearchAdapter.ItemViewHolder>() {

    private var mDataSet = mutableListOf<Any>()


    companion object {
        const val TYPE_TRACK_ITEM = 0
        const val TYPE_EMPTY = 1
    }

//    fun setItemList(_itemList: MutableList<TrackListItem>) {
//        mDataSet.clear()
//        if (_itemList.size > 0) {
//            mDataSet.addAll(_itemList)
//        } else {
//            mDataSet.add(EmptyItem())
//        }
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    override fun getItemId(p0: Int): Long {
        return when (mDataSet[p0]) {
//            is TrackListItem -> (mDataSet[p0] as TrackListItem).trackEntity.trackId.toLong()
            else -> 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (mDataSet[position]) {
//            is TrackListItem -> TYPE_TRACK_ITEM
//            is EmptyItem -> TYPE_EMPTY
            else -> TYPE_EMPTY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return when (viewType) {
//            TYPE_TRACK_ITEM -> ItemViewHolder(R.layout.item_track, parent)
            else -> ItemViewHolder(R.layout.item_search_list, parent)
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        when (holder.binding) {
//            is ItemTrackBinding -> {
//                val item = mDataSet[position] as TrackListItem
//                holder.binding.item = item
//                holder.binding.vm = viewModel
//                holder.binding.tvTime.text = item.trackEntity.recentTime?.let {CommonFunction.convertDateString(it)}
//            }
//            is ItemTrackEmptyBinding -> {
//                holder.binding.vm = viewModel
//            }
        }
    }

    class ItemViewHolder (
        layoutId: Int,
        parent: ViewGroup,
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
    ):RecyclerView.ViewHolder(binding.root)

    class EmptyItem()
}