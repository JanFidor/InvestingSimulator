package com.example.investingsimulator.room.templates

import androidx.room.*

@Dao
interface RoomDAO<T> {
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