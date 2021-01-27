package ru.eugenerogov.ui.currencyDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ru.eugenerogov.R
import ru.eugenerogov.databinding.CurrencyDetailFragmentBinding

@AndroidEntryPoint
class CurrencyDetail : Fragment(R.layout.currency_detail_fragment) {
    companion object {
        val TAG: String = CurrencyDetail::class.java.simpleName
    }

    private val viewModel: CurrencyDetailViewModel by viewModels()
//    val args: CurrencyDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CurrencyDetailFragmentBinding.bind(view)

    }

}