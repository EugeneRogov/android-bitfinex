package ru.eugenerogov.ui.currencyDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import missing.namespace.R
import missing.namespace.databinding.CurrencyDetailFragmentBinding

@AndroidEntryPoint
class CurrencyDetail : Fragment(R.layout.currency_detail_fragment) {
    companion object {
        val TAG: String = CurrencyDetail::class.java.simpleName
    }

    private val viewModel: CurrencyDetailViewModel by viewModels()
    private val args: CurrencyDetailArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(context, args.ticker.uuid.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CurrencyDetailFragmentBinding.bind(view)

    }

}