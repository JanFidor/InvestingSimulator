package com.example.investingsimulator.Room_Fuckery.templates

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavourite
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDAO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

open class RoomRepository(application: Application) {
    val db = StockDB.getInstance(application)
    open val stockDao: StockFavouriteDAO = db.stockFavouriteDAO
    private lateinit var _allStock: MutableLiveData<List<Stock>>

    init{
        Observable
            .fromSingle<List<Stock>> {stockDao.getAll()}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({stock -> _allStock = MutableLiveData(stock)}, {e -> Log.e("Room",
                "Asynchronous call, BoughtStock getAll() \n ${e.message}")})
    }

    val allStock: LiveData<List<Stock>>
        get() =_allStock

    fun create(stock: StockFavourite) = stockDao.insert(stock)
    fun delete(stock: StockFavourite) = stockDao.delete(stock)

}