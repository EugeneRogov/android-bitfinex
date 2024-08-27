package ru.eugenerogov.data.remote

import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

interface WebSocketService {

    fun getCurrency()

    @Singleton
    class Service
    @Inject constructor(
        client: OkHttpClient
    ) : WebSocketService {

        override fun getCurrency() {
            TODO("Not yet implemented")
        }

    }
}