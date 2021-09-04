package com.example.investingsimulator.models.stockModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.example.investingsimulator.models.DateIntervals
import com.example.investingsimulator.models.StockAnalysis
import com.example.investingsimulator.models.TextFormatting
import com.example.investingsimulator.retrofit.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

open class StockTemplate(private val stockData: StockTemplateRoom){

    val change: LiveData<Double> = liveData {
        val data = RetrofitInstance.getQuote(symbol)// loadUser is a suspend function.
        emit(data?.change_percentage ?: 0.0)
    }

    val symbol = stockData.symbol
    val description = stockData.description

    private val _data: MutableLiveData<StockAnalysis> by lazy{
        val end = DateIntervals.getCalculatedDate(0)
        val start = DateIntervals.getCalculatedDate(-30)
        val observable = RetrofitInstance.getHistory(stockData.symbol, start, end)

        Log.d("check", "2")

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { _data.value = StockAnalysis(it.history.day.toList()) }

        MutableLiveData(StockAnalysis(null))
    }
    val data: LiveData<StockAnalysis> = _data

    open val isSellable: Boolean = false

    /*    val data by lazy {
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
                            // TODO FIXXXXXXXXXXX
                            analysis = StockAnalysis(arrayOf())
                        }
                    }
                }
            })

            analysis}*/

    // TODO Make factory for single and a list
    companion object{
        fun create(stockData: StockTemplateRoom): StockTemplate{
            return StockTemplate(stockData)
        }

        fun create(stockData: List<StockTemplateRoom>): List<StockTemplate>{
            return stockData.map{StockTemplate(it)}
        }

    }
}