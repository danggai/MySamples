package com.example.kakaobank_2021.ui.main.storage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kakaobank_2021.R
import com.example.kakaobank_2021.databinding.MainStorageFragmentBinding
import com.example.kakaobank_2021.ui.main.MainViewModel
import com.example.kakaobank_2021.ui.main.search.MainSearchAdapter
import kr.danal.app.damoum.core.EventObserver
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainStorageFragment : Fragment() {

    companion object {
        fun newInstance() = MainStorageFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainStorageFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_storage_fragment, container,false);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        initLiveData()
    }


    private fun initLiveData() {
        viewModel.lvEventDataSetChanged.observe(viewLifecycleOwner, EventObserver {
            Log.d("storageFragment", "lvEventDataSetChanged")
            if (it) {
                (binding.rvSavedList.adapter as MainStorageAdapter).notifyDataSetChanged()
            }
        })

        viewModel.lvEventSaveItem.observe(viewLifecycleOwner, EventObserver { position ->
            Log.d("storageFragment", "lvEventSaveItem")
            (binding.rvSavedList.adapter as MainStorageAdapter).notifyDataSetChanged()
        })
    }

}