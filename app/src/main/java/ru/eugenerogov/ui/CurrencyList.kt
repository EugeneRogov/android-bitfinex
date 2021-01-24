package ru.eugenerogov.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.eugenerogov.R
import ru.eugenerogov.data.Currency
import ru.eugenerogov.databinding.CurrencyListFragmentBinding
import ru.eugenerogov.databinding.CurrencyListItemBinding

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

        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CurrencyListAdapter(model.currencyList.toList())
        }
    }

    private inner class CurrencyHolder(private val binding: CurrencyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cl: ConstraintLayout = binding.cl
        val ivFavorite: ImageView = binding.ivFavorite
        val tvCurrencyPair: TextView = binding.tvCurrencyPair
        val tvLastPrice: TextView = binding.tvLastPrice
        val tv24HoursChange: TextView = binding.tv24HoursChange
    }

    private inner class CurrencyListAdapter(var currencyList: List<Currency>) :
        RecyclerView.Adapter<CurrencyHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
            val binding = DataBindingUtil.inflate<CurrencyListItemBinding>(
                layoutInflater,
                R.layout.currency_list_item,
                parent,
                false
            )
            return CurrencyHolder(binding)
        }

        override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
            val currency = currencyList[position]
            holder.apply {
                tvCurrencyPair.text = currency.currencyPair
                tvLastPrice.text = currency.lastPrice.toString()
                tv24HoursChange.text = currency._24HoursChange.toString()
                cl.setOnClickListener {
                    findNavController().navigate(R.id.action_currencyList_to_currencyDetail)
                }
            }
        }

        override fun getItemCount() = currencyList.size

    }

}