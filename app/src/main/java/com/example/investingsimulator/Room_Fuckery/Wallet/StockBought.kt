package com.example.investingsimulator.Room_Fuckery.Wallet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bought_stock_database")
data class StockBought(
    @PrimaryKey(autoGenerate = false)
    val symbol : String,
    val price: Double,
    var amount: Double,
    var cost: Double,
    )
