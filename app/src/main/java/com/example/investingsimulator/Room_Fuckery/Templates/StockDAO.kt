package com.example.investingsimulator.Room_Fuckery.Templates


interface StockDAO {
    fun addStock(stock: Stock)

    fun deleteStock(stock: Stock)

    fun getAll(): List<Stock>
}