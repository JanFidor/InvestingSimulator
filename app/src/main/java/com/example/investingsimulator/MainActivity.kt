package com.example.investingsimulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.investingsimulator.API_Fuckery.MarketHistorySingle
import com.example.investingsimulator.API_Fuckery.QuoteDataWrapper
import com.example.investingsimulator.API_Fuckery.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

