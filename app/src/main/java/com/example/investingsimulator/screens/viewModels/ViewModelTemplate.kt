package com.example.investingsimulator.screens.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.investingsimulator.room.templates.RepositoryTemplateRoom
import com.example.investingsimulator.room.templates.StockTemplateRoom
import com.example.investingsimulator.models.stockModel.StockTemplate

open class ViewModelTemplate<T : StockTemplateRoom>(application: Application) : AndroidViewModel(application) {
    //private val _repository = RecipeRepository(application)

    protected open val _repository = RepositoryTemplateRoom<T>(application)

    private val stockAll: List<StockTemplate> = StockTemplate.create(_repository.allStock.value ?: listOf())

    protected val _stockVisible: MutableLiveData<List<StockTemplate>> = MutableLiveData(stockAll)
    val stockVisible: LiveData<List<StockTemplate>>
        get() = _stockVisible

    val searched: MutableLiveData<String> = MutableLiveData("")

    protected open fun filterStock() {
        _stockVisible.value = stockAll.filter {
            it.stockData.symbol.length >= (searched.value?.length ?: 0) &&
                    it.stockData.symbol.slice(searched.value?.indices ?: 0..0)  == searched.value
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