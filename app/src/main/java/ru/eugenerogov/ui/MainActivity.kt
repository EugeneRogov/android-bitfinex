package ru.eugenerogov.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.eugenerogov.R
import ru.eugenerogov.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.tbMain)

        supportActionBar?.title = "Title"

        title = getString(R.string.currency_list)

        val navController = findNavController(R.id.nav_host_main)
        navController.setGraph(R.navigation.main_graph)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.currencyList -> {
                    title = getString(R.string.currency_list)

                }
                R.id.currencyDetail -> {
                    title = getString(R.string.currency_detail)
                    binding.tbMain.setNavigationIcon(R.drawable.ic_arrow_back)
                    binding.tbMain.setNavigationOnClickListener {
                        navController.popBackStack()
                    }

                }
            }
        }
    }

}