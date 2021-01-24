package ru.eugenerogov.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.eugenerogov.R
import ru.eugenerogov.databinding.CurrencyDetailFragmentBinding

class CurrencyDetail : Fragment(R.layout.currency_detail_fragment) {
    companion object {
        val TAG: String = CurrencyDetail::class.java.simpleName
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CurrencyDetailFragmentBinding.bind(view)

    }

}