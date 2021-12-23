package com.example.investingsimulator.screens.viewModels

import android.app.Application
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.room.bought.RepositoryBoughtRoom
import com.example.investingsimulator.room.bought.StockBoughtRoom

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

    fun getAmount(stockName: String): Float = stockAll[stockName]?.stock?.amount?.toFloat() ?: 0F


    fun buy(stock: StockTemplate, amount: Float){
        val symbol = stock.symbol
        val description = stock.description
        val amount = amount.toDouble() + (stockAll[stock.symbol]?.stock?.amount ?: 0.0)

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
        val amount = (stockAll[stock.symbol]?.stock?.amount ?: 0.0) - amount.toDouble()

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
        return stockAll.map{it.value}.map{it.stock.amount.toFloat() * (it.last.value ?: 0f)}.sum()
    }

}