package com.example.investingsimulator.screens.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.retrofit.RetrofitInstance
import com.example.investingsimulator.room.bought.RepositoryBoughtRoom
import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.room.favourite.RepositoryFavouriteRoom
import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class ViewModelBought(application: Application) : ViewModelTemplate<StockBoughtRoom, StockBought>(application) {
    override val _repository = RepositoryBoughtRoom(application)

    override val stockAll: MutableMap<String, StockBought>
            by lazy {
                val map = mutableMapOf<String, StockBought>()
                _repository.getAll().forEach { map[it.symbol] = StockBought(it) }
                map}
    /*override val stockAll: MutableLiveData<List<StockTemplate>> =
        MutableLiveData(_repository.getAll().map{StockFavourite(it)})*/

    override fun add(stock: StockBoughtRoom) {
        _repository.create(stock)
        stockAll[stock.symbol] = StockBought(stock)
        filterStock()
    }

    override fun delete(stock: StockBoughtRoom) {
        _repository.delete(stock)
        stockAll.remove(stock.symbol)
        filterStock()
    }

    fun update(stock: StockBoughtRoom) {
        _repository.update(stock)
        stockAll[stock.symbol] = StockBought(stock)
        filterStock()
    }

    fun getAmount(stockName: String): Float = stockAll[stockName]?.stockData?.amount?.toFloat() ?: 0F


    fun buy(stock: StockTemplate, amount: Float){
        val symbol = stock.symbol
        val description = stock.description
        val amount = amount.toDouble() + (stockAll[stock.symbol]?.stockData?.amount ?: 0.0)

        // TODO PRICE
        val price = 0.0
        val stock = StockBoughtRoom(symbol, description, amount,price)

        if(symbol in stockAll) {
            update(stock)
        }
        else{
            add(stock)
        }

        // TODO Update funds
    }

    fun sell(stock: StockTemplate, amount: Float){
        val symbol = stock.symbol
        val description = stock.description
        val amount = (stockAll[stock.symbol]?.stockData?.amount ?: 0.0) - amount.toDouble()

        // TODO PRICE
        val price = 0.0
        val stock = StockBoughtRoom(symbol, description, amount,price)

        if(amount == 0.0) {
            delete(stock)
        }
        else{
            update(stock)
        }
    }

    fun getValue(): Float{
        return stockAll.map{it.value}.map{it.stockData}.map{it.amount.toFloat() * it.price.toFloat()}.sum()
    }

}