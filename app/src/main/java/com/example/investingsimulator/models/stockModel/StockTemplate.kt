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

abstract class StockTemplate(stock: StockTemplateRoom) : Serializable{
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
        val end = DateIntervals.getCalculatedDate(-1)
        val start = DateIntervals.getCalculatedDate(-31)
        val observable = RetrofitInstance.getHistory(stock.symbol, start, end)

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()) {
                    val open = it.first().open ?: 1f
                    val close = it.last().close ?: 1f
                    Log.d("history_api", "$open   $close")
                    _change.value = ((close / open) - 1) * 100
                    _last.value = it.last().close
                    _data.value = StockAnalysis(it)
                }
           }, {Log.e("api error", it.message.toString())})
    }

}