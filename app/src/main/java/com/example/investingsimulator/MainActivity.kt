package com.example.investingsimulator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.investingsimulator.API_Fuckery.MarketHistorySingle
import com.example.investingsimulator.API_Fuckery.QuoteDataWrapper
import com.example.investingsimulator.API_Fuckery.RetrofitInstance
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Response

// passing layout id as param is a BITCH
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        val stocks = "AAPL"

        val call1 = RetrofitInstance.InterfaceAPI.getCurrent(stocks)
        call1.enqueue(object : retrofit2.Callback<QuoteDataWrapper>{
            override fun onFailure(call: Call<QuoteDataWrapper>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(call: Call<QuoteDataWrapper>, response: Response<QuoteDataWrapper>?) {
                response?.let {
                    if (response.isSuccessful) Log.d("api", response.body().toString())
                }
            }
        })

        val start = "2021-08-10"
        val end = "2021-08-10"
        val call2 = RetrofitInstance.InterfaceAPI.getShortHistory(stocks, start, end)
        call2.enqueue(object : retrofit2.Callback<MarketHistorySingle> {
            override fun onFailure(call: Call<MarketHistorySingle>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(call: Call<MarketHistorySingle>, response: Response<MarketHistorySingle>?) {
                response?.let {
                    if (response.isSuccessful) Log.d("api", response.body().toString())
                }

            }
        })
    }

}

