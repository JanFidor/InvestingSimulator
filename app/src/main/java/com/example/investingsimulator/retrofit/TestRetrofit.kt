package com.example.investingsimulator.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface TestRetrofit {
    @GET("/v1/markets/quotes")
    fun getCurrent(@Query("symbols") symbols: String): Call<QuoteDataWrapper>

    @GET("/v1/markets/history")
    fun getShortHistory(@Query("symbol") symbols: String,
                    @Query("start") start: String,
                    @Query("end") end: String,): Call<MarketHistorySingle>

    @GET("/v1/markets/history")
    fun getLongHistory(@Query("symbol") symbols: String,
                       @Query("start") start: String,
                       @Query("end") end: String,): Call<MarketHistoryMultiple>

    @GET("/v1/markets/lookup")
    fun getSymbols(@Query("q") symbol: String): Call<SymbolsWrapper2>

    @GET("/v1/markets/lookup")
    fun getSymbol(@Query("q") symbol: String): Call<SymbolWrapper2>
}