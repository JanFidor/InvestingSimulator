package com.example.investingsimulator.Room_Fuckery.Favourite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBought
import com.example.investingsimulator.Room_Fuckery.templates.StockDAO


@Dao
interface StockFavouriteDAO : StockDAO<StockFavourite> {

    @Query("SELECT * FROM favourite_stock_database")
    override fun getAll(): List<StockFavourite>
}