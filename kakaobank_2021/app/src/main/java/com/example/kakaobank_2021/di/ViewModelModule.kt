package com.example.kakaobank_2021.di

import com.example.kakaobank_2021.ui.main.MainViewModel
import com.example.kakaobank_2021.ui.main.search.MainSearchViewModel
import com.example.kakaobank_2021.ui.main.storage.MainStorageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { MainStorageViewModel(get()) }
    viewModel { MainSearchViewModel(get()) }
}