package ru.eugenerogov.data

import androidx.lifecycle.LiveData
import ru.eugenerogov.data.local.db.TickerDao
import ru.eugenerogov.data.remote.Ticker
import java.util.*
import java.util.concurrent.Executors
import javax.inject.Inject

class TickerRepository @Inject constructor(private val tickerDao: TickerDao) {

    private val executor = Executors.newSingleThreadExecutor()

    fun getTickers(): LiveData<List<Ticker>> = tickerDao.getTickers()

    fun getTicker(id: UUID): LiveData<Ticker?> = tickerDao.getTicker(id)

    fun updateTicker(ticker: Ticker) {
        executor.execute { tickerDao.updateTicker(ticker) }
    }

    fun addTicker(ticker: Ticker) {
        executor.execute { tickerDao.addTicker(ticker) }
    }

}