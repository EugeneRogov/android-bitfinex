package ru.eugenerogov.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.eugenerogov.R
import ru.eugenerogov.databinding.CurrencyListFragmentBinding

class CurrencyList : Fragment(R.layout.currency_list_fragment) {
    companion object {
        val TAG: String = CurrencyList::class.java.simpleName
    }

    private lateinit var model: CurrencyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(CurrencyListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CurrencyListFragmentBinding.bind(view)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_currencyList_to_currencyDetail)
        }

    }

}