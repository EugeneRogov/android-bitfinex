package ru.eugenerogov.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.eugenerogov.data.remote.Ticker

@Database(entities = [Ticker::class], version = 1)
@TypeConverters(TickerTypeConverters::class)
abstract class TickerDatabase : RoomDatabase() {

    abstract fun tickerDao(): TickerDao
}