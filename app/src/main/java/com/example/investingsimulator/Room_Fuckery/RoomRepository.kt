package com.example.investingsimulator.Room_Fuckery

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavourite
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDAO
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDB
import com.example.investingsimulator.Room_Fuckery.Templates.Stock
import com.example.investingsimulator.Room_Fuckery.Templates.StockDAO
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBought
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBoughtDAO
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBoughtDB

class RoomRepository(application: Application) {
    private val stockBoughtDB = StockBoughtDB.getInstance(application)
    private val _boughtDao: StockBoughtDAO = stockBoughtDB.stockDAO

    private val stockFavouriteDB = StockFavouriteDB.getInstance(application)
    private val _favouriteDao: StockFavouriteDAO = stockFavouriteDB.stockDAO

    private val _allBought = MutableLiveData (_boughtDao.getAll())
    val allRecipes: LiveData<List<StockBought>>
        get() =_allBought


    private val _allFavourite = MutableLiveData (_favouriteDao.getAll())
    val allFavourite: LiveData<List<StockFavourite>>
        get() =_allFavourite


    fun create(stock: Stock){
        val dao: StockDAO? = when(stock){
            is StockBought -> _boughtDao
            is StockFavourite -> _favouriteDao
            else -> null
        }
        dao?.addStock(stock)
    }
    fun delete(stock: Stock){
        val dao: StockDAO? = when(stock){
            is StockBought -> _boughtDao
            is StockFavourite -> _favouriteDao
            else -> null
        }
        dao?.deleteStock(stock)
    }

    fun update(stock: StockBought) = _boughtDao.updateStock(stock)

}