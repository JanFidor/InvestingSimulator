package com.example.investingsimulator.Room_Fuckery.Favourite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_stock_database")
data class StockFavourite(
    val symbol: String,
    var cost: Int,
){
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
