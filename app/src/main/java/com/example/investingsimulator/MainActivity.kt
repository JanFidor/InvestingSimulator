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

        /*val stocks = "AAPL"
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
        call2.enqueue(object : Callback<MarketHistorySingle> {
            override fun onFailure(call: Call<MarketHistorySingle>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(call: Call<MarketHistorySingle>, response: Response<MarketHistorySingle>?) {
                response?.let {
                    if (response.isSuccessful) Log.d("api", response.body().toString())
                }

            }
        })

        val start = "2021-08-27"
        val end2 = "2021-08-27"
        val call3 = RetrofitInstance.InterfaceAPI.getLongHistory(stocks, start, end2)
        call3.enqueue(object : Callback<MarketHistoryMultiple> {
            override fun onFailure(call: Call<MarketHistoryMultiple>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(call: Call<MarketHistoryMultiple>, response: Response<MarketHistoryMultiple>?) {
                response?.let {
                    if (response.isSuccessful) Log.d("api", response.body().toString())
                }

            }
        })


        val call2 = RetrofitInstance.InterfaceAPI.getSymbols("googl")
        call2.enqueue(object : retrofit2.Callback<SymbolsWrapper2>{
            override fun onFailure(call: Call<SymbolsWrapper2>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(call: Call<SymbolsWrapper2>, response: Response<SymbolsWrapper2>?) {
                response?.let {
                    if (response.isSuccessful) Log.d("api", response.body().toString())
                }
            }
        })

        val call3 = RetrofitInstance.InterfaceAPI.getSymbol("aapl")
        call3.enqueue(object : retrofit2.Callback<SymbolWrapper2>{
            override fun onFailure(call: Call<SymbolWrapper2>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(call: Call<SymbolWrapper2>, response: Response<SymbolWrapper2>?) {
                response?.let {
                    if (response.isSuccessful) Log.d("api", response.body().toString())
                }
            }
        })
        */


    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun initializeFunds(){
        // TODO make funds a constant

        val sharedPrefs = this.getPreferences(Context.MODE_PRIVATE) ?: return
        if (!sharedPrefs.contains("FUNDS")) {
            with (sharedPrefs.edit()) {
                putFloat("FUNDS", 1000F)
                apply()
            }
        }
    }

}

