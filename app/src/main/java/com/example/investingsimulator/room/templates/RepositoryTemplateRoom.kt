package com.example.investingsimulator.room.templates

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

open class RepositoryTemplateRoom<T : StockTemplateRoom>(application: Application) {
    protected val db = StockDB.getInstance(application)
    protected open val stockDao: RoomDAO<T>? = null

    fun create(obj: T) = stockDao?.insert(obj)
    fun delete(obj: T) = stockDao?.delete(obj)
    fun update(obj: T) = stockDao?.update(obj)

    fun getAll(): List<T> = stockDao?.getAll() ?: listOf()

}