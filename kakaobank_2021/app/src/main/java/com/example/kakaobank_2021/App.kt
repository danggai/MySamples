package com.example.kakaobank_2021

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kakaobank_2021.di.NetworkModule
import com.example.kakaobank_2021.di.ViewModelModule
import com.example.kakaobank_2021.di.repositoryModule
import com.example.kakaobank_2021.ui.main.MainFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    @KoinExperimentalAPI
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            androidLogger(Level.ERROR)
            modules(listOf(
                NetworkModule,
                repositoryModule,
                ViewModelModule,
            ))
        }
    }
}