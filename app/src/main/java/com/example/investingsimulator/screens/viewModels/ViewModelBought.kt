package com.example.investingsimulator.screens.viewModels

import android.app.Application
import com.example.investingsimulator.models.stockModel.StockBought
import com.example.investingsimulator.models.stockModel.StockTemplate
import com.example.investingsimulator.room.bought.RepositoryBoughtRoom
import com.example.investingsimulator.room.bought.StockBoughtRoom


class ViewModelBought(application: Application) : ViewModelTemplate<StockBoughtRoom, StockBought>(application) {
    override val repository = RepositoryBoughtRoom(application)

    override val stockAll: MutableMap<String, StockBought>
            by lazy {
                val map = mutableMapOf<String, StockBought>()
                repository.getAll().forEach { map[it.symbol] = StockBought(it) }
                map}

    override fun add(stock: StockBoughtRoom) {
        repository.create(stock)
        stockAll[stock.symbol] = StockBought(stock)
        filterStock()
    }

    override fun delete(stock: StockBoughtRoom) {
        repository.delete(stock)
        stockAll.remove(stock.symbol)
        filterStock()
    }

    fun update(stock: StockBoughtRoom) {
        repository.update(stock)
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
        return stockAll.map{it.value}.map{it.stockData.amount.toFloat() * (it.last.value ?: 0f)}.sum()
    }

}