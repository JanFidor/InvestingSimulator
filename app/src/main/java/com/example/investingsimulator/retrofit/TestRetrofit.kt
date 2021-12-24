package com.example.investingsimulator.retrofit

import com.example.investingsimulator.retrofit.modelsJSON.MarketHistoryMultiple
import com.example.investingsimulator.retrofit.modelsJSON.SymbolWrapper2
import com.example.investingsimulator.retrofit.modelsJSON.SymbolsWrapper2
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface TestRetrofit {
    @GET("/v1/markets/history")
    fun getHistory(@Query("symbol") symbols: String,
                    @Query("start") start: String,
                    @Query("end") end: String,): Observable<MarketHistoryMultiple>

    @GET("/v1/markets/lookup")
    fun getSymbols(@Query("q") symbol: String): Observable<SymbolsWrapper2>

    @GET("/v1/markets/lookup")
    fun getSymbol(@Query("q") symbol: String): Observable<SymbolWrapper2>
}