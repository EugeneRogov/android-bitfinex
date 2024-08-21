package ru.eugenerogov.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import missing.namespace.R
import missing.namespace.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.currencyList -> {
                    binding.tbMain.title = getString(R.string.currency_list)
                    binding.tbMain.navigationIcon = null
                }
                R.id.currencyDetail -> {
                    binding.tbMain.title = getString(R.string.currency_detail)
                    binding.tbMain.setNavigationIcon(R.drawable.ic_arrow_back)
                    binding.tbMain.setNavigationOnClickListener {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

}