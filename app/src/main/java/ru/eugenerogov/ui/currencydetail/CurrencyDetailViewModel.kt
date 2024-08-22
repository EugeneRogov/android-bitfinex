package ru.eugenerogov.ui.currencydetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailViewModel @Inject constructor(

) : ViewModel() {
    companion object {
        val TAG: String = CurrencyDetailViewModel::class.java.simpleName
    }

}