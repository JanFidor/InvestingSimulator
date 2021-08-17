package com.example.investingsimulator.Room_Fuckery.Wallet

import androidx.room.*
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDAO
import com.example.investingsimulator.Room_Fuckery.templates.StockDAO


@Dao
interface StockBoughtDAO : StockDAO<StockBought>{
    /*@Insert
    fun addStock(stock: StockBought)

    @Insert
    fun addStocks(vararg stock: StockBought)

    @Delete
    fun deleteStock(stock: StockBought)*/

    /*@Update
    fun updateStock(stock: StockBought)*/

    @Query("SELECT * FROM bought_stock_database")
    override fun getAll(): List<StockBought>

    /*@Query("SELECT * FROM bought_stock_database WHERE id = :id")
    fun getId(id: Int): Observable<StockBought?>*/


}