package ru.eugenerogov

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.eugenerogov.data.TickerRepository

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        TickerRepository.initialize(this)
    }
}