package com.example.investingsimulator.API_Fuckery

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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


    fun getCurrent(symbols: String): Call<QuoteDataWrapper>{
        return InterfaceAPI.getCurrent(symbols)
    }

    /*fun getHistory(symbols: String, start: String, end: String,): ManagerClass{
        val stockData = ManagerClass(InterfaceAPI.getHistory(symbols, start, end))
        return stockData
    }

    fun getLongHistory(symbols: String, start: String, end: String,): Call<MarketHistoryMultiple>{
        return InterfaceAPI.getShortHistory(symbols, start, end)
    }*/

}