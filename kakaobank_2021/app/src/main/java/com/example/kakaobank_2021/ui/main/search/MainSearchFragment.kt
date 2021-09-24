package com.example.kakaobank_2021.ui.main.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kakaobank_2021.R
import com.example.kakaobank_2021.databinding.MainSearchFragmentBinding
import com.example.kakaobank_2021.ui.main.MainViewModel
import com.example.kakaobank_2021.ui.main.storage.MainStorageFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainSearchFragment : Fragment() {

    companion object {
        fun newInstance() = MainStorageFragment()
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

//        binding.tvSearch.setOnClickListener(this)
    }

}