package ru.eugenerogov.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.eugenerogov.data.TickerRepository
import ru.eugenerogov.data.local.db.TickerDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTickerRepository(tickerDao: TickerDao): TickerRepository {
        return TickerRepository(tickerDao)
    }

}