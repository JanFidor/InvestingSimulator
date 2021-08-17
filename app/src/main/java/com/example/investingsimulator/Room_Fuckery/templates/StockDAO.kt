package com.example.investingsimulator.Room_Fuckery.templates

import androidx.room.*
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBought

@Dao
interface StockDAO<T> {
    @Insert
    fun insert(stock: T)

    @Insert
    fun insert(vararg stock: T)

    @Delete
    fun delete(stock: T)
    
    @Update
    fun update(stock : T)


    fun getAll(): List<T>
}