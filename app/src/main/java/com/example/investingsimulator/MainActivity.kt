package com.example.investingsimulator

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    lateinit var navController: NavController
    val TOKEN_KEY = "api_token"

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
        fetchConfig()
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initializeFunds(){
        val sharedPrefs = this.getPreferences(Context.MODE_PRIVATE) ?: return
        if (!sharedPrefs.contains("FUNDS")) {
            with (sharedPrefs.edit()) {
                putFloat("FUNDS", 1000F)
                apply()
            }
        }
    }

    private fun fetchConfig(){
        val remoteConfig = initializeRemoteConfig()

        remoteConfig
            .fetchAndActivate()
            .addOnSuccessListener {useRemoteToken(remoteConfig)}
            .addOnFailureListener {e -> Log.e("Error fetching config", e.message.toString())}
    }

    private fun initializeRemoteConfig(): FirebaseRemoteConfig{
        val defaultToken = "Token"

        val config = FirebaseRemoteConfig.getInstance()
        config.setDefaultsAsync(mapOf(TOKEN_KEY  to defaultToken))
        return  config
    }

    private fun useRemoteToken(remoteConfig: FirebaseRemoteConfig){
        val remoteToken = remoteConfig.getString(TOKEN_KEY)
        RetrofitInstance.setToken(remoteToken)
    }

}

