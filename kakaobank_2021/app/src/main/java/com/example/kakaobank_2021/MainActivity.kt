package com.example.kakaobank_2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kakaobank_2021.di.NetworkModule
import com.example.kakaobank_2021.di.ViewModelModule
import com.example.kakaobank_2021.di.repositoryModule
import com.example.kakaobank_2021.ui.main.MainFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}