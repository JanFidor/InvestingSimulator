package com.example.investingsimulator.Room_Fuckery.Wallet

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavourite
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDAO
import com.example.investingsimulator.Room_Fuckery.templates.Stock

@Entity(tableName = "bought_stock_database")
data class StockBought(
    @PrimaryKey(autoGenerate = false)
    override val symbol : String,
    val price: Double,
    var amount: Double,
    var cost: Double,
    ) : Stock(symbol)
