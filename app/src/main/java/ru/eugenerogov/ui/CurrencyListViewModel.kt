package ru.eugenerogov.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.eugenerogov.data.Currency

class CurrencyListViewModel : ViewModel() {
    companion object {
        val TAG: String = CurrencyListViewModel::class.java.simpleName
    }

    val currencyList = mutableSetOf<Currency>()

    init {
        for (i in 0 until 3) {
            val currency = Currency()
            currency.currencyPair = "ETH/USD"
            currency.lastPrice = 1334.0
            currency._24HoursChange = 6.7F
            currencyList += currency
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }

}