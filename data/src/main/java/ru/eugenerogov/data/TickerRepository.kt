package ru.eugenerogov.data

import androidx.lifecycle.LiveData
import ru.eugenerogov.data.local.db.TickerDao
import ru.eugenerogov.data.remote.Ticker
import java.util.*
import javax.inject.Inject

class TickerRepository @Inject constructor(private val tickerDao: TickerDao) {

    fun getTickers(): LiveData<List<Ticker>> = tickerDao.getTickers()

    fun getTicker(id: UUID): LiveData<Ticker?> = tickerDao.getTicker(id)

}