package ru.eugenerogov.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import ru.eugenerogov.data.local.db.TickerDatabase
import ru.eugenerogov.data.remote.Ticker
import java.util.*

class TickerRepository private constructor(context: Context) {
    companion object {

        private const val TICKER_DATABASE = "ticker-database"

        private var INSTANCE: TickerRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = TickerRepository(context)
            }
        }

        fun get(): TickerRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

    private val database: TickerDatabase = Room.databaseBuilder(
        context.applicationContext,
        TickerDatabase::class.java,
        TICKER_DATABASE
    ).build()

    private val tickerDao = database.tickerDao()

    fun getTickers(): LiveData<List<Ticker>> = tickerDao.getTickers()

    fun getTicker(id: UUID): LiveData<Ticker?> = tickerDao.getTicker(id)

}