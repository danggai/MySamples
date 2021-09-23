package com.example.kakaobank_2021.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.kakaobank_2021.R
import com.example.kakaobank_2021.databinding.MainFragmentBinding
import com.example.kakaobank_2021.ui.main.search.MainSearchFragment
import com.example.kakaobank_2021.ui.main.storage.MainStorageFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container,false);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val pagerAdapter = MainAdapter(requireActivity())

        pagerAdapter.addFragment(MainSearchFragment())
        pagerAdapter.addFragment(MainStorageFragment())

        binding.vpMain.adapter = pagerAdapter

        TabLayoutMediator(binding.tlTop, binding.vpMain) { tab, position ->
            tab.text = when (position) {
                0 -> "검색 결과"
                1 -> "내 보관함"
                else -> ""
            }
        }.attach()

    }

}