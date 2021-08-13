package com.example.investingsimulator.Room_Fuckery.Wallet

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investingsimulator.Room_Fuckery.Templates.Stock

@Entity(tableName = "bought_stock_database")
data class StockBought(
    val symbol: String,
    var amount: Int,
    var price: Int,
    var cost: Int,
) : Stock() {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}
