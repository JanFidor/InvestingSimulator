package com.example.investingsimulator.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.StockAnalysis
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.google.gson.JsonSyntaxException
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException
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

    fun getSearchedStocks(search: String): Observable<List<SymbolData>>{
        var observable: Observable<List<SymbolData>> = Observable.just(listOf())
        observable =
        try {
            val call = InterfaceAPI.getSymbols(search)
            call.map {
                for(data in it.securities.security)Log.d("search", "call: ${data.symbol}, ${data.description}")
                RetrofitParser.getSymbols(it)}
        }
        catch (e : OnErrorNotImplementedException){
            Log.d("search", "catched")
            val call = InterfaceAPI.getSymbol(search)
            call.map {
                Log.d("search",
                    "call: ${it.securities.security.symbol}  ${it.securities.security.description}")

                RetrofitParser.getSymbol(it)}
        }
        /*catch (e : IllegalThreadStateException){
            val call = InterfaceAPI.getSymbol(search)
            call.map {
                Log.d("search",
                    "call: ${it.securities.security.symbol}  ${it.securities.security.description}")
                RetrofitParser.getSymbol(it)}
        }*/


        return observable
    }
    fun getQuote(symbol: String): Observable<Quote?>{
        return InterfaceAPI.getQuote(symbol)
            .map{RetrofitParser.getQuote(it)}
    }

    suspend fun getQuoteS(symbol: String): Quote?{
        return RetrofitParser.getQuote(InterfaceAPI.getQuoteS(symbol))
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