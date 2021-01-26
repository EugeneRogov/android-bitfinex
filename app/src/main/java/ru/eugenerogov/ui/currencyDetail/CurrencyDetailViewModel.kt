package ru.eugenerogov.ui.currencyDetail

import android.util.Log
import androidx.lifecycle.ViewModel

class CurrencyDetailViewModel : ViewModel() {
    companion object {
        val TAG: String = CurrencyDetailViewModel::class.java.simpleName
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }

}