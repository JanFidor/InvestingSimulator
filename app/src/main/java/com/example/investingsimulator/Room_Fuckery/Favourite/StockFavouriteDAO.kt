package com.example.investingsimulator.Room_Fuckery.Favourite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBought
import io.reactivex.rxjava3.core.Observable


@Dao
interface StockFavouriteDAO {
    @Insert
    fun addStock(stock: StockFavourite)

    @Delete
    fun deleteStock(stock: StockFavourite)

    @Query("SELECT * FROM favourite_stock_database")
    fun getAll(): Observable<List<StockFavourite>>

    /*@Query("SELECT * FROM favourite_stock_database WHERE id = :id")
    fun getId(id: Int): Observable<StockBought?>*/
}