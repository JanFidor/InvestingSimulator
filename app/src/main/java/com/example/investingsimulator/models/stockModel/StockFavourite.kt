package com.example.investingsimulator.models.stockModel

import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteRoom
import com.example.investingsimulator.Room_Fuckery.templates.StockTemplateRoom

class StockFavourite (stockData: StockTemplateRoom): StockTemplate(stockData) {
    override val isSellable: Boolean
        get() = false

    override val stockData = stockData as StockFavouriteRoom
}