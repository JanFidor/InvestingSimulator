package com.example.investingsimulator.models.stockModel

import com.example.investingsimulator.room.bought.StockBoughtRoom
import com.example.investingsimulator.room.templates.StockTemplateRoom

class StockBought(stockData: StockTemplateRoom, observe: Boolean): StockTemplate(stockData, observe) {
    override val isSellable: Boolean
        get() = true

    /*override val stockData = stockData as StockBoughtRoom*/
}