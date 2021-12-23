package com.example.investingsimulator.room.favourite

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.investingsimulator.room.templates.StockTemplateRoom


@Entity(tableName = "favourite_stock_database")
data class StockFavouriteRoom(
    @PrimaryKey(autoGenerate = false)
    override val symbol : String,
    override val description: String) : StockTemplateRoom()