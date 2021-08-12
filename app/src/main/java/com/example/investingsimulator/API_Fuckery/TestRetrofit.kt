package com.example.investingsimulator.API_Fuckery

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TestRetrofit {
    @GET("/v1/markets/quotes")
    fun test(@Query("symbols") symbols: String): Call<Model>

    @GET("/v1/markets/history")
    fun testHistory(@Query("symbol") symbols: String,
                    @Query("start") start: String,
                    @Query("end") end: String,): Call<History>
}