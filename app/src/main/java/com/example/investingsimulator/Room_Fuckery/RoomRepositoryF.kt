package com.example.investingsimulator.Room_Fuckery

import android.app.Application
import android.util.Log
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavourite
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDAO
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDB
import com.example.investingsimulator.Room_Fuckery.templates.Stock
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBought
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBoughtDAO
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBoughtDB
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
class RoomRepositoryF(application: Application) {
    private val stockBoughtDB = StockBoughtDB.getInstance(application)
    private val _boughtDao: StockBoughtDAO = stockBoughtDB.stockDAO

    private val stockFavouriteDB = StockFavouriteDB.getInstance(application)
    private val _favouriteDao: StockFavouriteDAO = stockFavouriteDB.stockDAO

    private lateinit var _allBought: List<StockBought>
    private lateinit var _allFavourite: List<StockFavourite>


    init{
        Observable
            .fromSingle<List<StockBought>> {_boughtDao.getAll()}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({stock -> _allBought = stock}, {e -> Log.e("Room",
                "Asynchronous call, BoughtStock getAll() \n ${e.message}")})

        Observable
            .fromSingle<List<StockFavourite>> {_favouriteDao.getAll()}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({stock -> _allFavourite = stock}, {e -> Log.e("Room",
                "Asynchronous call, BoughtStock getAll() \n ${e.message}")})
    }

    val allRecipes: List<StockBought>
        get() =_allBought

    val allFavourite: List<StockFavourite>
        get() =_allFavourite


    fun create(stock: Stock){
        when(stock){
            is StockBought -> _boughtDao.addStock(stock)
            is StockFavourite -> _favouriteDao.addStock(stock)
        }
    }
    fun delete(stock: Stock){
        when(stock){
            is StockBought -> _boughtDao.deleteStock(stock)
            is StockFavourite -> _favouriteDao.deleteStock(stock)
        }
    }

    fun update(stock: StockBought) = _boughtDao.updateStock(stock)

}*/
