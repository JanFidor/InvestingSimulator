package com.example.investingsimulator.room.favourite

import androidx.room.Dao
import androidx.room.Query
import com.example.investingsimulator.room.templates.RoomDAO


@Dao
interface StockFavouriteDAO : RoomDAO<StockFavouriteRoom> {

    @Query("SELECT * FROM favourite_stock_database")
    override fun getAll(): List<StockFavouriteRoom>
}