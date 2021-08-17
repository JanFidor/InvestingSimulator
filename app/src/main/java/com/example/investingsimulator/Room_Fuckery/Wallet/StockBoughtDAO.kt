package com.example.investingsimulator.Room_Fuckery.Wallet

import androidx.room.*
import com.example.investingsimulator.Room_Fuckery.templates.RoomDAO



@Dao
interface StockBoughtDAO : RoomDAO<StockBought>{
    @Query("SELECT * FROM bought_stock_database")
    override fun getAll(): List<StockBought>
}