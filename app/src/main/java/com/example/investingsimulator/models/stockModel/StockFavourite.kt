package com.example.investingsimulator.models.stockModel

import com.example.investingsimulator.room.favourite.StockFavouriteRoom
import com.example.investingsimulator.room.templates.StockTemplateRoom

class StockFavourite (stockData: StockTemplateRoom): StockTemplate(stockData) {
    override val isSellable: Boolean
        get() = false

    override val stockData = stockData as StockFavouriteRoom
}