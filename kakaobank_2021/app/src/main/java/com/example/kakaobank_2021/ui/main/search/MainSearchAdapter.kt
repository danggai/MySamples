package com.example.kakaobank_2021.ui.main.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kakaobank_2021.R
import com.example.kakaobank_2021.data.local.SearchedListItem
import com.example.kakaobank_2021.databinding.ItemSearchedListBinding
import com.example.kakaobank_2021.ui.main.MainViewModel


class MainSearchAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<MainSearchAdapter.ItemViewHolder>() {

    private var mDataSet = mutableListOf<SearchedListItem>()

    companion object {
        const val TYPE_ITEM = 0
    }

    fun setItemList(_itemList: MutableList<SearchedListItem>) {
        _itemList.sortBy { it.datetime }

        mDataSet.clear()
        mDataSet.addAll(_itemList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    override fun getItemId(p0: Int): Long {
        return mDataSet[p0].thumbnail.hashCode().toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(R.layout.item_searched_list, parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mDataSet[position]

        when(holder.binding) {
            is ItemSearchedListBinding -> {
                holder.binding.vm = viewModel
                holder.binding.item = mDataSet[position]
                holder.binding.tvDate.text = mDataSet[position].datetime.removeRange(18, mDataSet[position].datetime.length-1).replace("T", " ")
                holder.binding.tvState.text = if (mDataSet[position] in viewModel.savedList.value) "저장됨" else ""

                Glide.with(holder.itemView.context)
                    .load(item.thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .apply(RequestOptions().fitCenter())
                    .into(holder.binding.ivThumbnail)
            }
        }
    }

    class ItemViewHolder (
        layoutId: Int,
        parent: ViewGroup,
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
    ):RecyclerView.ViewHolder(binding.root)

//    class ItemViewHolder(binding: ItemSearchedListBinding) : RecyclerView.ViewHolder(binding.root) {
//        private val binding: ItemSearchedListBinding = binding
//        fun bind(viewModel: MainViewModel?, item: SearchedListItem) {
//            binding.vm = viewModel
//            binding.item = item
//            binding.executePendingBindings()
//        }
//    }
}