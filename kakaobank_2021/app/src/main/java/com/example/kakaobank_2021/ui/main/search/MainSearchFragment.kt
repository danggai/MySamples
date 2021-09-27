package com.example.kakaobank_2021.ui.main.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kakaobank_2021.R
import com.example.kakaobank_2021.databinding.MainSearchFragmentBinding
import com.example.kakaobank_2021.ui.main.MainViewModel
import com.example.kakaobank_2021.ui.main.storage.MainStorageAdapter
import kr.danal.app.damoum.core.EventObserver
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainSearchFragment : Fragment() {

    companion object {
        fun newInstance() = MainSearchFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainSearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_search_fragment, container,false);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvSearchList.recycledViewPool.setMaxRecycledViews(0, 999999)

        initLiveData()
    }

    private fun initLiveData() {
        viewModel.lvEventDataSetChanged.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                Log.d("searchFragment", "lvEventDataSetChanged")
                (binding.rvSearchList.adapter as MainSearchAdapter).notifyDataSetChanged()
            }
        })

        viewModel.lvEventSaveItem.observe(viewLifecycleOwner, EventObserver { position ->
            Log.d("searchFragment", "lvEventSaveItem")
            (binding.rvSearchList.adapter as MainSearchAdapter).notifyItemChanged(position)
        })
    }
}