package com.example.investingsimulator.Room_Fuckery.templates

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavourite
import com.example.investingsimulator.Room_Fuckery.Favourite.StockFavouriteDAO
import com.example.investingsimulator.Room_Fuckery.Wallet.StockBought
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

open class RoomRepository<T>(application: Application) {
    val db = StockDB.getInstance(application)
    open val stockDao: StockDAO<T>? = null
    private lateinit var _allStock: MutableLiveData<List<T>>

    init{
        Observable
            .fromSingle<List<T>> {stockDao?.getAll()}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({stock -> _allStock = MutableLiveData(stock)}, {e -> Log.e("Room",
                "Asynchronous call, BoughtStock getAll() \n ${e.message}")})
    }

    val allStock: LiveData<List<T>>
        get() =_allStock

    fun create(stock: T) = stockDao?.insert(stock)
    fun delete(stock: T) = stockDao?.delete(stock)
    fun update(stock: T) = stockDao?.update(stock)

}