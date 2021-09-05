package com.example.investingsimulator.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TestRetrofit {
    @GET("/v1/markets/quotes")
    fun getQuote(@Query("symbols") symbols: String): Observable<QuoteWrapper2>

    @GET("/v1/markets/quotes")
    suspend fun getQuoteS(@Query("symbols") symbols: String): QuoteWrapper2

    @GET("/v1/markets/history")
    fun getHistory(@Query("symbol") symbols: String,
                    @Query("start") start: String,
                    @Query("end") end: String,): Observable<MarketHistoryMultiple>

    @GET("/v1/markets/history")
    fun getLongHistory(@Query("symbol") symbols: String,
                       @Query("start") start: String,
                       @Query("end") end: String,): Call<MarketHistoryMultiple>

    @GET("/v1/markets/history")
    suspend fun getLongHistoryO(@Query("symbol") symbols: String,
                       @Query("start") start: String,
                       @Query("end") end: String,): MarketHistoryMultiple

    @GET("/v1/markets/lookup")
    fun getSymbols(@Query("q") symbol: String): Observable<SymbolsWrapper2>

    @GET("/v1/markets/lookup")
    fun getSymbol(@Query("q") symbol: String): Observable<SymbolWrapper2>

    @GET("/v1/markets/lookup")
    suspend fun getSymbolsO(@Query("q") symbol: String): SymbolsWrapper2

    @GET("/v1/markets/lookup")
    suspend fun getSymbolO(@Query("q") symbol: String): SymbolWrapper2

}