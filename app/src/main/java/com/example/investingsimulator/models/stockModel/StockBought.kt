package com.example.investingsimulator.models.stockModel

import com.example.investingsimulator.room.bought.StockBoughtRoom


class StockBought(override val stockData: StockBoughtRoom): StockTemplate(stockData) {
    override val symbol: String
        get() = stockData.symbol
    override val description: String
        get() = stockData.description
}