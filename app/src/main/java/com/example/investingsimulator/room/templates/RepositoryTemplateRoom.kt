package com.example.investingsimulator.room.templates

import android.app.Application


open class RepositoryTemplateRoom<T : StockTemplateRoom>(application: Application) {
    protected val db = StockDB.getInstance(application)
    protected open val stockDao: RoomDAO<T>? = null

    fun create(obj: T) = stockDao?.insert(obj)
    fun delete(obj: T) = stockDao?.delete(obj)
    fun update(obj: T) = stockDao?.update(obj)

    fun getAll(): List<T> = stockDao?.getAll() ?: listOf()

}