package com.example.investingsimulator.Room_Fuckery.Favourite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investingsimulator.Room_Fuckery.templates.Stock

@Entity(tableName = "favourite_stock_database")
open class StockFavourite(
    @PrimaryKey(autoGenerate = false)
    val symbol: String,
    var price: Double,
    ) : Stock()