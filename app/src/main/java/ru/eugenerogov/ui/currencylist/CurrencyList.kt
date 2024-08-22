package ru.eugenerogov.ui.currencylist

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.eugenerogov.R
import ru.eugenerogov.data.remote.Ticker
import ru.eugenerogov.databinding.CurrencyListFragmentBinding
import ru.eugenerogov.databinding.CurrencyListItemBinding

@AndroidEntryPoint
class CurrencyList : Fragment(R.layout.currency_list_fragment) {
    companion object {
        val TAG: String = CurrencyList::class.java.simpleName
    }

    private val viewModel: CurrencyListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CurrencyListFragmentBinding.bind(view)

        // observe refresh from server
        viewModel.ticker().observe(viewLifecycleOwner, {
            binding.rv.adapter?.notifyDataSetChanged()
            Toast.makeText(requireContext(), getString(R.string.refreshed), Toast.LENGTH_SHORT)
                .show()
        })

        // observe data base
        viewModel.tickerListLiveData.observe(viewLifecycleOwner) {
            Log.i(TAG, "Got tickers ${it.size}")
        }

        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CurrencyListAdapter(viewModel.tickerList)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private inner class CurrencyHolder(binding: CurrencyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cl: ConstraintLayout = binding.cl
        val ivFavorite: ImageView = binding.ivLogo
        val tvCurrencyPair: TextView = binding.tvCurrencyPair
        val tvLastPrice: TextView = binding.tvLastPrice
        val tv24HoursChange: TextView = binding.tv24HoursChange
    }

    private inner class CurrencyListAdapter(var currencyList: List<Ticker>) :
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
            val ticker = currencyList[position]
            holder.apply {
                Glide.with(requireContext()).load(ticker.urlIcon).into(ivFavorite)
                tvCurrencyPair.text = ticker.title
                tvLastPrice.text = ticker.lastPrice.toString()
                tv24HoursChange.text = ticker.dailyChange.toString()
                cl.setOnClickListener {
                    val directions =
                        CurrencyListDirections.actionCurrencyListToCurrencyDetail(ticker)
                    findNavController().navigate(directions)
                }
            }
        }

        override fun getItemCount() = currencyList.size

    }

}