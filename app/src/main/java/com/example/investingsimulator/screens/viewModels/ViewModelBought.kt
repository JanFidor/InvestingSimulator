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
                repository
                    .getAll()
                    .map{it.symbol to StockBought(it)}
                    .toMap()
                    .toMutableMap()}

    override fun addStock(stock: StockBoughtRoom) {
        repository.create(stock)
        stockAll[stock.symbol] = StockBought(stock)
        filterSavedStocks()
    }

    override fun deleteStock(stock: StockBoughtRoom) {
        repository.delete(stock)
        stockAll.remove(stock.symbol)
        filterSavedStocks()
    }

    private fun update(stock: StockBoughtRoom) {
        repository.update(stock)
        stockAll[stock.symbol] = StockBought(stock)
        filterSavedStocks()
    }

    fun getAmount(stockName: String): Float = stockAll[stockName]?.stockData?.amount?.toFloat() ?: 0F

    private fun initializeStockBoughtRoom(stock: StockTemplate, amount: Double): StockBoughtRoom{
        val symbol = stock.symbol
        val description = stock.description

        // TODO PRICE
        val price = 0.0

        return StockBoughtRoom(symbol, description, amount,price)
    }

    fun buy(stock: StockTemplate, amount: Float){
        val totalAmount = amount.toDouble() + (stockAll[stock.symbol]?.stockData?.amount ?: 0.0)
        val stockBought = initializeStockBoughtRoom(stock, totalAmount)

        if(stock.symbol in stockAll) {
            update(stockBought)
        }
        else{
            addStock(stockBought)
        }
    }

    fun sell(stock: StockTemplate, amount: Float){
        val totalAmount = (stockAll[stock.symbol]?.stockData?.amount ?: 0.0) - amount.toDouble()
        val stockBought = initializeStockBoughtRoom(stock, totalAmount)

        if(totalAmount == 0.0) {
            deleteStock(stockBought)
        }
        else{
            update(stockBought)
        }
    }

    fun getTotalWalletValue(): Float{
        return stockAll
            .map{it.value}
            .map{it.stockData.amount.toFloat() * (it.last.value ?: 0f)}
            .sum()
    }

}