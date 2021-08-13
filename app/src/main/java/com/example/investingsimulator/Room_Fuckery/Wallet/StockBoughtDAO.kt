package com.example.investingsimulator.Room_Fuckery.Wallet

import androidx.room.*
import com.example.investingsimulator.Room_Fuckery.Templates.StockDAO


@Dao
interface StockBoughtDAO: StockDAO {
    @Insert
    fun addStock(stock: StockBought)

    @Delete
    fun deleteStock(stock: StockBought)

    @Update
    fun updateStock(stock: StockBought)

    @Query("SELECT * FROM bought_stock_database")
    override fun getAll(): List<StockBought>

    /*@Query("SELECT * FROM bought_stock_database WHERE id = :id")
    fun getId(id: Int): Observable<StockBought?>*/


}