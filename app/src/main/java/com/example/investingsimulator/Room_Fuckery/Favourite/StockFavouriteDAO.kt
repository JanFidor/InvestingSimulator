package com.example.investingsimulator.Room_Fuckery.Favourite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.investingsimulator.Room_Fuckery.Templates.StockDAO


@Dao
interface StockFavouriteDAO: StockDAO {
    @Insert
    fun addStock(stock: StockFavourite)

    @Delete
    fun deleteStock(stock: StockFavourite)

    @Query("SELECT * FROM favourite_stock_database")
    override fun getAll(): List<StockFavourite>

    /*@Query("SELECT * FROM favourite_stock_database WHERE id = :id")
    fun getId(id: Int): Observable<StockBought?>*/
}