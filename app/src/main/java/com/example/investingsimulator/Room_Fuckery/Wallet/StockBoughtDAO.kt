package com.example.investingsimulator.Room_Fuckery.Wallet

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable


@Dao
interface StockBoughtDAO {
    @Insert
    fun addStock(stock: StockBought)

    @Delete
    fun deleteStock(stock: StockBought)

    @Query("SELECT * FROM bought_stock_database")
    fun getAll(): Observable<List<StockBought>>

    /*@Query("SELECT * FROM bought_stock_database WHERE id = :id")
    fun getId(id: Int): Observable<StockBought?>*/


}