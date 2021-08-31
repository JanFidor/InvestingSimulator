package com.example.investingsimulator.Room_Fuckery.Favourite

import androidx.room.Dao
import androidx.room.Query
import com.example.investingsimulator.Room_Fuckery.templates.RoomDAO


@Dao
interface StockFavouriteDAO : RoomDAO<StockFavouriteRoom> {

    @Query("SELECT * FROM favourite_stock_database")
    override fun getAll(): List<StockFavouriteRoom>
}