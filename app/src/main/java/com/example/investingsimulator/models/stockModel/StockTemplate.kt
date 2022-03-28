package com.example.investingsimulator.models.stockModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.example.investingsimulator.models.StockAnalysis
import com.example.investingsimulator.models.getCalculatedDate
import com.example.investingsimulator.retrofit.*
import com.example.investingsimulator.retrofit.modelsJSON.DayData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.Serializable


abstract class StockTemplate(val stock: StockTemplateRoom) : Serializable{
    private var _hasHistory = false
    val hasHistory: Boolean
            get() = _hasHistory

    abstract val stockData: StockTemplateRoom
    private val _text = MutableLiveData("")
    val text: LiveData<String>
        get() = _text

    abstract val symbol: String
    abstract val description: String

    private val _change = MutableLiveData(0f)
    val change: LiveData<Float>
        get() = _change


    private val _last = MutableLiveData(0f)
    val last: LiveData<Float>
        get() = _last

    private val _data: MutableLiveData<StockAnalysis?> = MutableLiveData(null)
    val data: LiveData<StockAnalysis?> = _data

    init{
        Log.d("access", stock.symbol)
        sendHistoryCall()
    }

    private fun sendHistoryCall(){
        val observable = initializeStockHistoryObservable(stock.symbol)

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _hasHistory = true
                    interpretStockHistory(it)
                },
                {Log.e("api error", it.message.toString())}
            )
    }

    fun getHistory(){
        if (!_hasHistory) sendHistoryCall()
    }


    private fun initializeStockHistoryObservable(stockSymbol: String): Observable<List<DayData>> {
        val end = getCalculatedDate(-1)
        val start = getCalculatedDate(-31)

        return RetrofitInstance.getHistory(stockSymbol, start, end)
    }

    private fun interpretStockHistory(stockHistory: List<DayData>){
        if (stockHistory.isEmpty()) return
        val (open, close) = getStockEdgeValues(stockHistory)
        assignPropertyValues(stockHistory, open, close)
    }

    private fun getStockEdgeValues(stockHistory: List<DayData>): Pair<Float, Float>{
        val open = stockHistory.first().open ?: 1f
        val close = stockHistory.last().close ?: 1f

        return Pair(open, close)
    }

    private fun assignPropertyValues(stockHistory: List<DayData>, open: Float, close: Float){
        _change.value = ((close / open) - 1) * 100
        _last.value = stockHistory.last().close
        _data.value = StockAnalysis(stockHistory)
    }



}