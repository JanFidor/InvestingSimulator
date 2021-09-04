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

    protected open val stockAll: MutableLiveData<List<StockTemplate>> by lazy { MutableLiveData(_repository.getAll().map{ StockTemplate(it) })}
    protected val _stockVisible: MutableLiveData<List<StockTemplate>> by lazy { MutableLiveData(stockAll.value)}
    val stockVisible: LiveData<List<StockTemplate>>
        get() = _stockVisible

    val searched: MutableLiveData<String> = MutableLiveData("")

    protected open fun filterStock() {
        Log.d("search", "searching")
        _stockVisible.value = stockAll.value?.filter {
            it.symbol.length >= (searched.value?.length ?: 0) &&
                    it.symbol.slice(searched.value?.indices ?: 0..0)  == searched.value
        }
    }

    fun add(stock: T) {
        _repository.create(stock)
        filterStock()
    }
    fun deleteRecipe(stock: T) {
        _repository.delete(stock)
        filterStock()
    }
    fun updateSearch(){
        filterStock()
    }
}