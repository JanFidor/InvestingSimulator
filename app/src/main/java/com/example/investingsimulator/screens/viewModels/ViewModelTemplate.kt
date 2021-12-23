package com.example.investingsimulator.screens.viewModels

import android.app.Application
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

    protected open fun filterSavedStocks(): List<U> {
        val list = getSearchedSavedStocks()
        _stockVisible.postValue(list)
        return list
    }

    private fun getSearchedSavedStocks(): List<U>{
        return stockAll.values.filter {isStockSearched(it)}
    }

    private fun isStockSearched(stock: U): Boolean{
        val correctLength = stock.symbol.length >= (searched.length)
        val correctSymbol = stock.symbol.slice(searched.indices)  == searched

        return correctSymbol && correctLength
    }

    abstract fun addStock(stock: T)
    abstract fun deleteStock(stock: T)
    fun updateSearch(query: String){
        searched = query.uppercase()
        filterSavedStocks()
    }

    fun getSize(): Int = stockAll.size
}