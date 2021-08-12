package com.example.investingsimulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.investingsimulator.API_Fuckery.History
import com.example.investingsimulator.API_Fuckery.Model
import com.example.investingsimulator.API_Fuckery.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stocks = "AAPL"

        val call1 = RetrofitInstance.InterfaceAPI.test(stocks)
        call1.enqueue(object : retrofit2.Callback<Model> {
            override fun onFailure(call: Call<Model>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(call: Call<Model>, response: Response<Model>?) {
                response?.let {
                    if (response.isSuccessful) Log.d("api", response.body().toString())
                }
            }
        })

        val start = "2021-08-11"
        val end = "2021-08-11"
        val call2 = RetrofitInstance.InterfaceAPI.testHistory(stocks, start, end)
        call2.enqueue(object : retrofit2.Callback<History> {
            override fun onFailure(call: Call<History>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(call: Call<History>, response: Response<History>?) {
                response?.let {
                    if (response.isSuccessful) Log.d("api", response.body().toString())

                }

            }
        })
    }
}

