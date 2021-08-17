package com.example.investingsimulator.Room_Fuckery.Wallet

import android.app.Application
import com.example.investingsimulator.Room_Fuckery.templates.RoomRepository

class RoomRepositoryBought(application: Application)
    : RoomRepository(application) {
    val boughtDao = db.stockBoughtDAO

    fun update(stock: StockBought) = boughtDao.update(stock)
    fun add(stock: StockBought) = boughtDao.insert(stock)
    fun delete(stock: StockBought) = boughtDao.delete(stock)
}