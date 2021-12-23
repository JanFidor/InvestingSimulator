package com.example.investingsimulator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


// passing layout id as param is a BITCH
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(this, navController)

        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        initializeFunds()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    
    private fun initializeFunds(){
        val sharedPrefs = this.getPreferences(Context.MODE_PRIVATE) ?: return
        if (!sharedPrefs.contains("FUNDS")) {
            with (sharedPrefs.edit()) {
                putFloat("FUNDS", R.integer.default_wallet_funds.toFloat())
                apply()
            }
        }
    }
}

