package com.example.hw5_retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.hw5_retrofit.MainApplication
import com.example.hw5_retrofit.R
import com.example.hw5_retrofit.common.invisible
import com.example.hw5_retrofit.common.viewBinding
import com.example.hw5_retrofit.common.visible
import com.example.hw5_retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            NavigationUI.setupWithNavController(bottomNav,navHostFragment.navController)

            navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.detailFragment) {
                    bottomNav.invisible()
                } else {
                    bottomNav.visible()
                }
            }
        }

        MainApplication.provideRetrofit()
    }
}