package com.example.investingsimulator.Room_Fuckery.Favourite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investingsimulator.Room_Fuckery.templates.Stock

@Entity(tableName = "favourite_stock_database")
data class StockFavourite(
    @PrimaryKey(autoGenerate = false)
    override val symbol : String,
    var price: Double,
    ) : Stock(symbol)