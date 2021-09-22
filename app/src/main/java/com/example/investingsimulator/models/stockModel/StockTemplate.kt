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

abstract class StockTemplate(open val stockData: StockTemplateRoom) : Serializable{
    private val _text = MutableLiveData("")
    val text: LiveData<String>
        get() = _text

    abstract val symbol: String
    abstract val description: String

    private val _change = liveData {
        /*val quote = RetrofitInstance.getQuoteS(symbol)
        Log.d("stock", "change value")
        _last.postValue(quote?.last ?: 0.0)
        emit(quote?.change_percentage ?: 0.0)*/
        val day = RetrofitInstance.getHistoryDay(symbol, DateIntervals.getCalculatedDate(-2))
        _last.postValue(day.close)
        val change = (day.close / day.open) - 1

        emit(change)
    }

    val change: LiveData<Double>
        get() = _change


    private val _last = MutableLiveData(0.0)
    val last: LiveData<Double>
        get() = _last

    private val _data: MutableLiveData<StockAnalysis> by lazy{
        Log.d("access", symbol)
        val end = DateIntervals.getCalculatedDate(-1)
        val start = DateIntervals.getCalculatedDate(-31)
        val observable = RetrofitInstance.getHistory(stockData.symbol, start, end)

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                _data.value = StockAnalysis(it)
            }

        MutableLiveData(null)
    }
    val data: LiveData<StockAnalysis> by lazy{ _data}
}