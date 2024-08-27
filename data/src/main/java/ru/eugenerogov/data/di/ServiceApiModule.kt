package ru.eugenerogov.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.eugenerogov.data.remote.WebSocketService
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * This module contains all services for work with API.
 *
 * For more info, see [KDoc](https://docs.bitfinex.com/docs/introduction).
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class ServiceApiModule {


    @Singleton
    @Provides
    fun provideWebSocketService(service: WebSocketService.Service): WebSocketService = service

}