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

    private val stockAll: MutableLiveData<List<StockTemplate>> by lazy{ MutableLiveData(_repository.getAll().map{ StockTemplate(it) })}
    protected val _stockVisible: MutableLiveData<List<StockTemplate>> by lazy{ MutableLiveData(stockAll.value)}
    val stockVisible: LiveData<List<StockTemplate>>
        get() = _stockVisible

    protected var searched: String = ""

    protected open fun filterStock() {
        Log.d("search", "filtering from database")
        _stockVisible.postValue(stockAll.value?.filter {
            it.symbol.length >= (searched.length) &&
                    it.symbol.slice(searched.indices)  == searched
        })
    }

    fun add(stock: T) {
        _repository.create(stock)
        filterStock()
    }
    fun deleteRecipe(stock: T) {
        _repository.delete(stock)
        filterStock()
    }
    fun updateSearch(query: String){
        searched = query
        filterStock()
    }
}