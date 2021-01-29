package ru.eugenerogov.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.eugenerogov.data.local.db.TickerDao
import ru.eugenerogov.data.local.db.TickerDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    companion object {
        private const val TICKER_DATABASE = "ticker-database"
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TickerDatabase {
        return Room.databaseBuilder(
            appContext,
            TickerDatabase::class.java,
            TICKER_DATABASE
        ).build()
    }

    @Provides
    fun provideTickerDao(database: TickerDatabase): TickerDao {
        return database.tickerDao()
    }

}
