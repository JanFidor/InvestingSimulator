package com.example.investingsimulator.retrofit

import android.database.Observable
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException


object RetrofitInstance {
    private const val TOKEN = "kGMhlocUonPNYwHKWL72zSqN8fQJ "
    private const val URL = "https://sandbox.tradier.com/"

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val newRequest = chain
                .request()
                .newBuilder()
                .header("Authorization", "Bearer $TOKEN")
                .header("Accept", "application/json")
                .build()
            return@Interceptor chain.proceed(newRequest)
        })
        .addInterceptor(HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()


    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()

    val InterfaceAPI: TestRetrofit = retrofit.create(TestRetrofit::class.java)


    fun getCurrent(symbol: String): Quote?{
        var data: Quote? = null
        val call = InterfaceAPI.getCurrent(symbol)
        call.enqueue(object : retrofit2.Callback<QuoteDataWrapper> {
            override fun onFailure(call: Call<QuoteDataWrapper>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(
                call: Call<QuoteDataWrapper>,
                response: Response<QuoteDataWrapper>?
            ){
                response?.let {
                    if (response.isSuccessful) data = response.body()?.quotes?.quote
                }
            }
        })

        return data
    }

    fun getHistory(symbol: String, start: String, end: String,): Array<DayData>{
        var list: Array<DayData> = arrayOf()
        val call = InterfaceAPI.getLongHistory(symbol, start, end)
        call.enqueue(object : retrofit2.Callback<MarketHistoryMultiple> {
            override fun onFailure(call: Call<MarketHistoryMultiple>, t: Throwable?) {
                Log.e("api", t?.message ?: "No message")
            }

            override fun onResponse(
                call: Call<MarketHistoryMultiple>,
                response: Response<MarketHistoryMultiple>?
            ){
                response?.let {
                    if (response.isSuccessful) list = response.body()?.data ?: arrayOf()
                }
            }
        })

        return list
    }

    fun getSearchedStocks(search: String): Array<String>{
        var list: Array<String> = arrayOf()

        try {
            val call1 = InterfaceAPI.getSymbols(search)
            call1.enqueue(object : retrofit2.Callback<SymbolsWrapper2> {
                override fun onFailure(call: Call<SymbolsWrapper2>, t: Throwable?) {
                    Log.e("api", t?.message ?: "No message")
                }

                override fun onResponse(
                    call: Call<SymbolsWrapper2>,
                    response: Response<SymbolsWrapper2>?
                ) {
                    response?.let {
                        if (response.isSuccessful) list = response.body()?.data ?: arrayOf()
                    }
                }
            })
        }
        catch (e : IllegalStateException){
            val call2 = InterfaceAPI.getSymbol(search)
            call2.enqueue(object : retrofit2.Callback<SymbolWrapper2>{
                override fun onFailure(call: Call<SymbolWrapper2>, t: Throwable?) {
                    Log.e("api", t?.message ?: "No message")
                }

                override fun onResponse(call: Call<SymbolWrapper2>, response: Response<SymbolWrapper2>?) {
                    response?.let {
                        if (response.isSuccessful) list = response.body()?.data ?: arrayOf()
                    }
                }
            })
        }
        return list
    }
}