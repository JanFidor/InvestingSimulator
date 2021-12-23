package com.example.investingsimulator.retrofit

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val TOKEN = "R6vMuvLo20ZMScWN6VvvGfTw4Lzn"
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    val InterfaceAPI: TestRetrofit = retrofit.create(TestRetrofit::class.java)

    fun getHistory(symbols: String, start: String, end: String)
    :Observable<List<DayData>> = InterfaceAPI.getHistory(symbols, start, end).map{RetrofitParser.getHistory(it)}

    fun getSearchedStocks(search: String): Observable<List<SymbolData>>{
        return InterfaceAPI.getSymbols(search).map{RetrofitParser.getSymbols(it)}
    }

    fun getSearchedStock(search: String): Observable<List<SymbolData>>{
        return InterfaceAPI.getSymbol(search).map{RetrofitParser.getSymbol(it)}
    }
}