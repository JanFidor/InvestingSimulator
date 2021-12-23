package com.example.investingsimulator.screens.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.room.templates.RepositoryTemplateRoom
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.example.investingsimulator.models.stockModel.StockTemplate

abstract class ViewModelTemplate<T : StockTemplateRoom, U : StockTemplate>(application: Application) : AndroidViewModel(application) {

    protected open val repository by lazy {RepositoryTemplateRoom<T>(application)}

    protected abstract val stockAll: MutableMap<String, U>

    protected val _stockVisible: MutableLiveData<List<U>>
        by lazy{ MutableLiveData(stockAll.map { it.value })}

    val stockVisible: LiveData<List<U>>
        get() = _stockVisible

    protected var searched: String = ""

    protected open fun filterStock(): List<U> {
        val list = stockAll
            .map { it.value }
            .filter {
                it.symbol.length >= (searched.length) &&
                it.symbol.slice(searched.indices)  == searched
        }
        _stockVisible.postValue(list)
        return list
    }

    abstract fun add(stock: T)
    abstract fun delete(stock: T)

    fun updateSearch(query: String){
        searched = query.uppercase()
        filterStock()
    }

    fun getSize(): Int = stockAll.size
}