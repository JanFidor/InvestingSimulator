package com.example.investingsimulator.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.StockAnalysis
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.google.gson.JsonSyntaxException
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    val InterfaceAPI: TestRetrofit = retrofit.create(TestRetrofit::class.java)


    /*fun getCurrent(symbols: String): Call<QuoteDataWrapper>{
        return InterfaceAPI.getCurrent(symbols)
    }
*/
    /*fun getHistory(symbol: String, start: String, end: String,): Array<DayData>{
        var list: Array<DayData> = arrayOf()
        return list
    }*/

    /*fun getSearchedStocks(search: String): List<String>{
        var list: List<String> = listOf()
        try {
            val call = InterfaceAPI.getSymbols(search)
            val response = call.execute()
            if(response.isSuccessful) list = RetrofitParser.getSymbols(response.body())
        }
        catch (e : JsonSyntaxException){
            val call = InterfaceAPI.getSymbol(search)
            val response = call.execute()
            if(response.isSuccessful) list = RetrofitParser.getSymbol(response.body())
        }
        return list
    }
*/
    suspend fun getQuote(symbol: String): Quote?{
        return RetrofitParser.getQuote(InterfaceAPI.getQuote(symbol))
    }

    /*suspend fun getHistory(symbols: String, start: String, end: String):List<DayData>{
        return RetrofitParser.getHistory(InterfaceAPI.getLongHistoryO(symbols, start, end))
    }*/
    fun getHistory(symbols: String, start: String, end: String)
    :Observable<MarketHistoryMultiple> = InterfaceAPI.getHistory(symbols, start, end)

    suspend fun getSearch(search: String): List<SymbolData>{
        val data =
        try {
            RetrofitParser.getSymbols(InterfaceAPI.getSymbolsO(search))}
        catch (e : JsonSyntaxException) {
            RetrofitParser.getSymbol(InterfaceAPI.getSymbolO(search))}

        return data
    }

}