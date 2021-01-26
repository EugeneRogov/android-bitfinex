package ru.eugenerogov.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.eugenerogov.R
import ru.eugenerogov.databinding.CurrencyDetailFragmentBinding

class CurrencyDetail : Fragment(R.layout.currency_detail_fragment) {
    companion object {
        val TAG: String = CurrencyDetail::class.java.simpleName
    }

    private lateinit var model: CurrencyDetailViewModel
//    val args: CurrencyDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(CurrencyDetailViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CurrencyDetailFragmentBinding.bind(view)

    }

}