package com.example.investingsimulator.Room_Fuckery.Favourite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investingsimulator.Room_Fuckery.templates.StockTemplateRoom

@Entity(tableName = "favourite_stock_database")
data class StockFavouriteRoom(
    @PrimaryKey(autoGenerate = false)
    override val symbol : String) : StockTemplateRoom()