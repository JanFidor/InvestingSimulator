package com.example.investingsimulator.models.stockModel

import com.example.investingsimulator.room.bought.StockBoughtRoom

class StockBought(override val stock: StockBoughtRoom): StockTemplate(stock) {

    override val symbol: String
        get() = stock.symbol
    override val description: String
        get() = stock.description
}