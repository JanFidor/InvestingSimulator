package com.example.investingsimulator.API_Fuckery

import com.example.investingsimulator.API_Fuckery.modelsJSON.StockDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

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

    /*@GET("/v1/markets/history")
    fun getHistory(@Query("symbol") symbols: String,
                       @Query("start") start: String,
                       @Query("end") end: String,): Call<StockDataWrapper>*/
}