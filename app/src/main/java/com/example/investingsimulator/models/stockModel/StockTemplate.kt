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
import java.io.Serializable

open class StockTemplate(protected val stockData: StockTemplateRoom, observe: Boolean) : Serializable{


    private val _text = MutableLiveData("")
    val text: LiveData<String>
        get() = _text

    val symbol = stockData.symbol
    val description = stockData.description

    private val _change = liveData {
        val quote = RetrofitInstance.getQuoteS(symbol)
        Log.d("stock", "change value")
        emit(quote?.change_percentage ?: 0.0)
    }

    val change: LiveData<Double>
        get() = _change


    protected val _observed = MutableLiveData(observe)
    val observed: LiveData<Boolean>
        get() = _observed



    private val _data: MutableLiveData<StockAnalysis> by lazy{
        Log.d("access", symbol)
        val end = DateIntervals.getCalculatedDate(0)
        val start = DateIntervals.getCalculatedDate(-30)
        val observable = RetrofitInstance.getHistory(stockData.symbol, start, end)

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                _data.value = StockAnalysis(it.history.day.toList())
            }

        MutableLiveData(null)
    }
    val data: LiveData<StockAnalysis> by lazy{ _data}

    open val isSellable: Boolean = false

    // TODO Make factory for single and a list
    companion object{
        fun create(stockData: StockTemplateRoom, observe: Boolean): StockTemplate{
            return StockTemplate(stockData, observe)
        }

        fun create(stockData: List<StockTemplateRoom>, observe: List<Boolean>): List<StockTemplate>{
            return stockData.mapIndexed{ind, it -> StockTemplate(it, observe[ind])}
        }

    }
}