package com.example.investingsimulator.room.bought

import androidx.room.*
import com.example.investingsimulator.room.templates.RoomDAO


@Dao
interface StockBoughtDAO : RoomDAO<StockBoughtRoom>{
    @Query("SELECT * FROM bought_stock_database")
    override fun getAll(): List<StockBoughtRoom>
}