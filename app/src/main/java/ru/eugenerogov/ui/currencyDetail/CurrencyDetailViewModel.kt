package ru.eugenerogov.ui.currencyDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class CurrencyDetailViewModel @ViewModelInject constructor(

) : ViewModel() {
    companion object {
        val TAG: String = CurrencyDetailViewModel::class.java.simpleName
    }

    override fun onCleared() {
        super.onCleared()

    }

}