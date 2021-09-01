package com.example.investingsimulator.models.stockModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.retrofit.MarketHistoryMultiple
import com.example.investingsimulator.retrofit.QuoteDataWrapper
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.example.investingsimulator.models.DateIntervals
import com.example.investingsimulator.models.StockAnalysis
import com.example.investingsimulator.models.TextFormatting
import retrofit2.Response

open class StockTemplate(open val stockData: StockTemplateRoom){

    private val _change = MutableLiveData(0.0)
    val change: LiveData<Double>
        get() = _change

    private val _fullName = MutableLiveData("")
    val fullName: LiveData<String>
        get() = _fullName

    open val isSellable: Boolean = false

    init {
        val data = RetrofitInstance.getCurrent(stockData.symbol)
        _change.value = data?.change_percentage
        _fullName.value = data?.description

    }



    val data by lazy {
        val start = DateIntervals.getCalculatedDate(0)
        val end = DateIntervals.getCalculatedDate(-30)
        var analysis: StockAnalysis? = null

        val data = RetrofitInstance.getHistory(stockData.symbol, start, end)
        analysis = StockAnalysis(data)

        analysis}


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