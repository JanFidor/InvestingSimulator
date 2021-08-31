package com.example.investingsimulator.models.stockModel

import com.example.investingsimulator.Room_Fuckery.Wallet.StockBoughtRoom
import com.example.investingsimulator.Room_Fuckery.templates.StockTemplateRoom

class StockBought(stockData: StockTemplateRoom): StockTemplate(stockData) {
    override val isSellable: Boolean
        get() = true

    override val stockData = stockData as StockBoughtRoom
}