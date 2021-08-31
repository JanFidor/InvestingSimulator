package com.example.investingsimulator.Room_Fuckery.Wallet

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investingsimulator.Room_Fuckery.templates.StockTemplateRoom

@Entity(tableName = "bought_stock_database")
data class StockBoughtRoom(
    @PrimaryKey(autoGenerate = false)
    override val symbol : String,
    var amount: Double,
    var price: Double,
    ) : StockTemplateRoom() {
    fun buy(amount: Double, price: Double){
        this.price = ((amount * price) + (this.amount * this.price)) / (amount + this.amount)
        this.amount = (amount + this.amount)
    }
}
