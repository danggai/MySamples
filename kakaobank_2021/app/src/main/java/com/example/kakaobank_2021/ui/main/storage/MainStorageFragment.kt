package com.example.kakaobank_2021.ui.main.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kakaobank_2021.R

class MainStorageFragment : Fragment() {

    companion object {
        fun newInstance() = MainStorageFragment()
    }

    private lateinit var viewModel: MainStorageFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainStorageFragment::class.java)
        // TODO: Use the ViewModel
    }

}