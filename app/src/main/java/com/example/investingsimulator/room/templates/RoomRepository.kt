package com.example.investingsimulator.room.templates

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

open class RoomRepository<T>(application: Application) {
    val db = StockDB.getInstance(application)
    open val stockDao: RoomDAO<T>? = null
    private val _allStock =  MutableLiveData<List<T>> ()

    init{
        Observable
            .fromSingle<List<T>> {stockDao?.getAll()}
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({obj -> _allStock.value = obj}, {e -> Log.e("Room",
                "Asynchronous call, BoughtStock getAll() \n ${e.message}")})
    }

    val allStock: LiveData<List<T>>
        get() =_allStock

    fun create(obj: T) = stockDao?.insert(obj)
    fun delete(obj: T) = stockDao?.delete(obj)
    fun update(obj: T) = stockDao?.update(obj)

}