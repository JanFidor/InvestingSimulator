package com.example.investingsimulator.screens.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.models.stockModel.StockFavourite
import com.example.investingsimulator.room.templates.RepositoryTemplateRoom
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.example.investingsimulator.models.stockModel.StockTemplate

abstract class ViewModelTemplate<T : StockTemplateRoom>(application: Application) : AndroidViewModel(application) {
    //private val _repository = RecipeRepository(application)

    protected open val _repository by lazy {RepositoryTemplateRoom<T>(application)}

    protected open val stockAll: MutableLiveData<List<StockTemplate>> by lazy{ MutableLiveData(_repository.getAll().map{ StockTemplate(it, true) })}
    protected val _stockVisible: MutableLiveData<List<StockTemplate>> by lazy{ MutableLiveData(stockAll.value)}
    val stockVisible: LiveData<List<StockTemplate>>
        get() = _stockVisible

    protected var searched: String = ""

    protected open fun getList(): List<StockTemplate> = _repository.getAll().map{ StockTemplate(it, true) }

    protected open fun filterStock() {
        val list = getList()
        Log.d("search", "filtering from database")
        _stockVisible.postValue(list.filter {
            it.symbol.length >= (searched.length) &&
                    it.symbol.slice(searched.indices)  == searched
        })
    }

    fun add(stock: T) {
        _repository.create(stock)
        filterStock()
    }
    fun delete(stock: T) {
        _repository.delete(stock)
        filterStock()
    }
    fun updateSearch(query: String){
        searched = query
        filterStock()
    }
}