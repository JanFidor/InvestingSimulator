package com.example.investingsimulator.models.stockModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.API_Fuckery.MarketHistoryMultiple
import com.example.investingsimulator.API_Fuckery.QuoteDataWrapper
import com.example.investingsimulator.API_Fuckery.RetrofitInstance
import com.example.investingsimulator.Room_Fuckery.templates.StockTemplateRoom
import com.example.investingsimulator.models.DateIntervals
import com.example.investingsimulator.models.StockAnalysis
import com.example.investingsimulator.models.TextFormatting
import retrofit2.Response

abstract class StockTemplate(open val stockData: StockTemplateRoom){
    val change = MutableLiveData("")
    val fullName = MutableLiveData("")
    abstract val isSellable: Boolean

    init {
        val call = RetrofitInstance.InterfaceAPI.getCurrent(stockData.symbol)
        call.enqueue(object : retrofit2.Callback<QuoteDataWrapper>{
            override fun onFailure(call: retrofit2.Call<QuoteDataWrapper>, t: Throwable) {
                Log.e("api", t.message ?: "No message")
            }

            override fun onResponse(call: retrofit2.Call<QuoteDataWrapper>, response: Response<QuoteDataWrapper>?) {
                response?.let {
                    if (response.isSuccessful) {
                        val data = response.body()?.quotes?.quote
                        change.value = TextFormatting.getPercentText(data?.change_percentage ?: 0.0)
                        fullName.value = data?.description ?: ""
                    }
                }
            }
        })
    }



    val data by lazy {
        val start = DateIntervals.getCalculatedDate(0)
        val end = DateIntervals.getCalculatedDate(-30)
        var analysis: StockAnalysis? = null

        val call = RetrofitInstance.InterfaceAPI.getLongHistory(stockData.symbol, start, end)
        call.enqueue(object : retrofit2.Callback<MarketHistoryMultiple>{
            override fun onFailure(call: retrofit2.Call<MarketHistoryMultiple>, t: Throwable) {
                Log.e("api", t.message ?: "No message")
            }

            override fun onResponse(call: retrofit2.Call<MarketHistoryMultiple>, response: Response<MarketHistoryMultiple>?) {
                response?.let {
                    if (response.isSuccessful) {
                        analysis = StockAnalysis(response.body()?.data ?: arrayOf())
                    }
                }
            }
        })

        analysis}


    // TODO Make factory for single and a list
}