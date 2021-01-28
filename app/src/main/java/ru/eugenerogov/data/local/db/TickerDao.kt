package ru.eugenerogov.data.local.db

import androidx.room.Dao
import androidx.room.Query
import ru.eugenerogov.data.remote.Ticker
import java.util.*

@Dao
interface TickerDao {
    @Query("SELECT * FROM ticker")
    fun getTickers(): List<Ticker>

//    @Query("SELECT * FROM ticker WHERE uuid=(:uuid)")
//    fun getTicker(id: UUID): Ticker?

}