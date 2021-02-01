package ru.eugenerogov.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.eugenerogov.data.remote.Ticker
import java.util.*

@Dao
interface TickerDao {
    @Query("SELECT * FROM ticker")
    fun getTickers(): LiveData<List<Ticker>>

    @Query("SELECT * FROM ticker WHERE id=(:id)")
    fun getTicker(id: UUID): LiveData<Ticker?>

    @Update
    fun updateTicker(ticker: Ticker)

    @Insert
    fun addTicker(ticker: Ticker)

}