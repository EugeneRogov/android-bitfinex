package ru.eugenerogov.ui.currencyDetail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

class CurrencyDetailViewModel @ViewModelInject constructor(

) : ViewModel() {
    companion object {
        val TAG: String = CurrencyDetailViewModel::class.java.simpleName
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared")
    }

}