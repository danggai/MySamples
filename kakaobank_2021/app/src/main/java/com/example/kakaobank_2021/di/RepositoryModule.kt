package com.example.kakaobank_2021.di

import com.example.kakaobank_2021.data.api.ApiInterface
import com.example.kakaobank_2021.data.api.ApiRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single(createdAtStart = false) { ApiRepository(get<Retrofit>().create(ApiInterface::class.java)) }
}